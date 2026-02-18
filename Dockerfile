# Intentionally insecure Dockerfile for scanning
FROM ubuntu:20.04

# Weak: installing tools without pinning + running as root
RUN apt-get update && apt-get install -y curl openssh-server

# Weak: add secrets into image
ENV AWS_SECRET_ACCESS_KEY="wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY"

# Weak: expose ssh
EXPOSE 22 8080

CMD ["bash", "-lc", "echo 'lab container' && sleep 3600"]
