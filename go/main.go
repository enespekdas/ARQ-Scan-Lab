package main

import (
	"crypto/md5"
	"crypto/tls"
	"fmt"
	"io"
	"net/http"
	"os/exec"
)

func main() {
	sum := md5.Sum([]byte("test")) // weak hash
	fmt.Printf("md5: %x\n", sum)

	// Weak: InsecureSkipVerify
	tr := &http.Transport{TLSClientConfig: &tls.Config{InsecureSkipVerify: true}}
	client := &http.Client{Transport: tr}
	resp, _ := client.Get("https://example.com")
	if resp != nil {
		fmt.Println("status:", resp.StatusCode)
	}

	// Weak: command injection style (example only)
	out, _ := exec.Command("sh", "-c", "echo hello").Output()
	io.WriteString(io.Discard, string(out))
}
