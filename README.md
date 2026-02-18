# ARQ Security Platform – Scan Lab Project (Multi-language)

This repository is **intentionally insecure** and contains **fake secrets** and **known-bad patterns** to help you test
all scanning types (SAST patterns, secrets detection, dependency/vuln scanning, container/IaC misconfig, config leaks).

✅ Use **ONLY** in a lab environment.  
❌ Do NOT deploy this to production.

## What’s inside
- **Secrets**: API keys, tokens, private keys (fake), `.env`, config files, CI variables.
- **Crypto**: weak algorithms (MD5/SHA1, DES/3DES), insecure modes (ECB), predictable RNG, RSA-1024.
- **TLS/HTTP**: SSL verification disabled, insecure ciphers, plaintext HTTP endpoints.
- **Web vulns**: SQL injection, XSS, command injection samples.
- **Dependencies**: manifests with intentionally outdated/known-risk packages.
- **IaC**: Terraform / Kubernetes YAML / Dockerfile with misconfigurations.

## Quick usage
- Zip/unzip and run scans with ARQ.
- You do not need to build/run anything; scanning should find patterns statically.

## Disclaimer
All secrets are *dummy* values. Any resemblance to real credentials is accidental.
