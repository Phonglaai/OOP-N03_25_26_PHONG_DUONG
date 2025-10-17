#!/bin/bash
# Build script for Railway

echo "📦 Installing dependencies and building..."
mvn clean package -DskipTests

echo "✅ Build completed!"
