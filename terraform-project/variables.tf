variable "aws_region" {
  default = "us-east-1"
}

variable "instance_type" {
  default = "t2.nano"
}

variable "key_name" {
  description = "AWS Key Pair Name"
}

variable "ami_id" {
  default = "ami-0c02fb55956c7d316"
}