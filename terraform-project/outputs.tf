output "ec2_public_ip" {
  value = aws_instance.autopilot_ec2.public_ip
}