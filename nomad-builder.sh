#!/bin/bash
./mvnw clean install
docker build -t digital-nomad -f Dockerfile .
