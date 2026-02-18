const express = require("express");
const jwt = require("jsonwebtoken");
const axios = require("axios");
const child_process = require("child_process");

const app = express();
app.use(express.json());

// Weak: hardcoded JWT secret
const JWT_SECRET = "hardcoded-jwt-secret-not-for-prod";

// Weak: command injection
app.get("/ping", (req, res) => {
  const host = req.query.host || "127.0.0.1";
  child_process.exec("ping -c 1 " + host, (err, stdout, stderr) => {
    if (err) return res.status(500).send(stderr);
    res.send(stdout);
  });
});

// Weak: XSS (reflect user input)
app.get("/hello", (req, res) => {
  const name = req.query.name || "world";
  res.set("Content-Type", "text/html");
  res.send("<h1>Hello " + name + "</h1>");
});

// Weak: axios with rejectUnauthorized false (TLS verification disabled)
app.get("/fetch", async (req, res) => {
  const url = req.query.url || "https://example.com";
  const r = await axios.get(url, { httpsAgent: new (require("https").Agent)({ rejectUnauthorized: false }) });
  res.json({ status: r.status, data: ("" + r.data).slice(0, 200) });
});

// Weak: JWT with no expiration
app.post("/token", (req, res) => {
  const user = req.body.user || "guest";
  const token = jwt.sign({ sub: user, role: "admin" }, JWT_SECRET);
  res.json({ token });
});

app.listen(3000, () => console.log("lab node listening on 3000"));
