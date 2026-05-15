resource "aws_security_group" "autopilot_sg" {
  name = "autopilot-sg"

  ingress {
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  ingress {
    from_port   = 9091
    to_port     = 9091
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  ingress {
    from_port   = 9100
    to_port     = 9100
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

resource "aws_instance" "autopilot_ec2" {
  ami                    = var.ami_id
  instance_type          = var.instance_type
  key_name               = var.key_name
  vpc_security_group_ids = [aws_security_group.autopilot_sg.id]

  user_data = <<-EOF
              #!/bin/bash
              yum update -y

              amazon-linux-extras install docker -y

              service docker start

              usermod -aG docker ec2-user

              docker run -d \
                --name autopilot-backend \
                -p 9091:8080 \
                your-dockerhub-username/autopilot-sre

              docker run -d \
                --name node-exporter \
                -p 9100:9100 \
                prom/node-exporter
              EOF

  tags = {
    Name = "AutoPilot-SRE-Instance"
  }
}