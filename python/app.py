from flask import Flask, request
import hashlib
import requests
import os
from Crypto.Cipher import DES

app = Flask(__name__)

# Weak: hardcoded secret
APP_SECRET = "super-insecure-secret"

@app.get("/hash")
def weak_hash():
    s = request.args.get("s", "test")
    return hashlib.sha1(s.encode()).hexdigest()  # weak

@app.get("/tls")
def insecure_tls():
    url = request.args.get("url", "https://example.com")
    r = requests.get(url, verify=False, timeout=3)  # SSL verify disabled
    return str(r.status_code)

@app.get("/des")
def des_encrypt():
    key = b"8bytekey"
    cipher = DES.new(key, DES.MODE_ECB)  # weak algo + ECB
    pt = (request.args.get("pt","ABCDEFGH")[:8]).encode()
    return cipher.encrypt(pt).hex()

@app.get("/secret")
def env_secret():
    # secrets in env
    return os.environ.get("AWS_SECRET_ACCESS_KEY", "missing")

if __name__ == "__main__":
    app.run(port=5000)
