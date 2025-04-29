# 🏆 Futebola

**Futebola** é uma plataforma web voltada para a organização de jogos de futebol amador — o famoso rachão ou pelada com os amigos.  
O projeto permite cadastrar jogadores, criar grupos, agendar partidas, confirmar presença, gerenciar pagamentos e até estruturar pequenos campeonatos (pontos corridos, mata-mata, festivais, etc).

Este repositório contém o código-fonte principal da aplicação, atualmente em fase de desenvolvimento do MVP (Produto Mínimo Viável).

---

## 🚀 Objetivos do Projeto

- Facilitar a organização de partidas de futebol amador (futsal, society, campo).
- Gerenciar grupos, jogadores, pagamentos e arbitragem.
- Criar uma base para futuras funcionalidades como rankings, estatísticas e integração com WhatsApp.
- Servir como projeto de estudo Full-Stack (desenvolvimento + DevOps).

---

## 📌 Funcionalidades do MVP

- Autenticação de usuários (JWT)
- Cadastro de jogadores com informações básicas
- Criação de grupos e agendamento de partidas
- Confirmação de presença nos jogos
- Ambientes de QA e Produção usando containers
- Deploy contínuo com GitHub Actions

---

## 🛠️ Tecnologias Utilizadas

### 🔧 Backend
- **Java 21**
- **Spring Boot**
- **Spring Security (JWT)**
- **Spring Data JPA**
- **Gradle** (build)
- **Banco de dados: PostgreSQL**
- **Banco para testes locais: H2**

### 🎨 Frontend
- **React**
- **Vite** (opcional para projeto leve)
- **Axios** (para consumo de API)
- **React Router DOM**

### ☁️ DevOps & Deploy
- **Containers (Docker)**
- **Docker Compose**
- **GitHub Actions** (CI/CD)
- **Ambientes QA/PROD em VMs Linux (baixo custo)**

---

## 📦 Estrutura do Projeto