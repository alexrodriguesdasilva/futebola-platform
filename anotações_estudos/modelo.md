
# 🏆 Futebola - Modelo de Banco de Dados (ER)

Este diagrama representa o modelo Entidade-Relacionamento (ER) da primeira versão do projeto **Futebola**, uma plataforma web para organizar partidas de futebol amador — os famosos rachões.

## 🎯 Objetivos

- Cadastro de jogadores
- Organização de grupos de rachão
- Controle de presença nas partidas
- Registro de pagamentos (mensalidade ou avulso)
- Registro de quem confirmou e quem compareceu

---

## 🧱 Modelo de Dados (Entidades e Relacionamentos)

### 🧍‍♂️ Jogador
- `jogador_id` (PK)
- `nome`
- `apelido`
- `telefone`
- `data_nascimento`
- `posicao_preferida`

### 🏟️ Rachão
- `rachao_id` (PK)
- `nome`
- `local`
- `horario`
- `jogadores_necessarios`
- `mensalidade`
- `valor_avulso`

### 🤝 Jogador_Rachao (relação muitos para muitos)
- `jogador_rachao_id` (PK)
- `jogador_id` (FK)
- `rachao_id` (FK)
- `tipo_pagamento` (mensal, avulso, nenhum)

### 📅 Partida
- `partida_id` (PK)
- `rachao_id` (FK)
- `data_partida`
- `ocorreu`

### 📋 Presença
- `presenca_id` (PK)
- `jogador_id` (FK)
- `partida_id` (FK)
- `confirmou`
- `compareceu`

---

## 🗺️ Diagrama ER Visual

![Modelo ER](modelo_er_futebola.png)

---

## 📌 Observações

- Um jogador pode participar de vários rachões.
- Cada rachão pode ter várias partidas.
- Para cada partida, controlamos quem confirmou e quem compareceu.
- Registro de pagamento ajuda a distinguir quem está em dia.

---

## 📦 Futuras Expansões

- Rankings e estatísticas dos jogadores
- Gols, assistências e faltas
- Integração com WhatsApp
- Notificações e lembretes automáticos

---
