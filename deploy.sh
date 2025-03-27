#!/bin/bash
# deploy.sh - Script otimizado para construção

# Carrega variáveis do .env mantendo valores entre aspas
if [ -f .env ]; then
    set -o allexport
    source .env
    set +o allexport
fi

# Executa o build com Maven usando variáveis carregadas
./mvnw clean package -DskipTests

# Verificação do resultado
if [ $? -eq 0 ]; then
    echo "✅ Build realizado com sucesso"
    exit 0
else
    echo "❌ Falha no build"
    exit 1
fi