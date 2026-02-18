terraform {
  required_version = ">= 0.12"
}

# Weak: public S3-like bucket with ACL
resource "aws_s3_bucket" "public_bucket" {
  bucket = "arq-scan-lab-public-bucket"
  acl    = "public-read"
}

# Weak: security group open to the world
resource "aws_security_group" "open_sg" {
  name        = "open-sg"
  description = "open all ports for lab"

  ingress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}
