# Trabalho Prático 01 - CRUD em Servlet 🚀
**Instituto Federal de São Paulo (IFSP) - Campus Cubatão**
**Disciplina:** CBTSWE1 - ADS 571
**Professor:** Wellington Tuler Moraes

## 👥 Dupla
* Matheus Correia de França
* Davi Leite Coelho

## 📋 Sobre o Projeto
Este projeto consiste no desenvolvimento de uma aplicação web completa utilizando a arquitetura **Java Servlet** e o banco de dados **MySQL**. O objetivo é realizar as operações fundamentais de um CRUD (Create, Read, Update and Delete) em um sistema de gestão de funcionários.

## 🛠️ Tecnologias Utilizadas
* **Java:** Linguagem principal para lógica de Servlets.
* **MySQL:** Armazenamento persistente de dados (Tabela `user905` no banco `java`).
* **Apache Tomcat:** Servidor de aplicações web.
* **Bootstrap 5:** Framework para design responsivo e moderno das interfaces.

## 🌟 Funcionalidades e Diferenciais (Nota 10)
Além dos requisitos básicos do TP, implementamos recursos adicionais para garantir maior segurança e usabilidade:
* **Validação de E-mail Único:** O sistema verifica se o e-mail já existe no banco antes de permitir o cadastro, evitando duplicidade indesejada.
* **Confirmação de Exclusão:** Implementação de script JavaScript para solicitar confirmação do usuário antes de deletar qualquer registro da lista.
* **Padronização Visual:** Interface limpa e intuitiva utilizando Bootstrap para todos os formulários e tabelas.
* **Privacidade de Dados:** Máscara de segurança na exibição de senhas na lista geral de funcionários.

## ⚙️ Como executar
1. Clone o repositório.
2. Certifique-se de que o banco de dados `java` e a tabela `user905` estejam criados no seu MySQL.
3. Importe o projeto no Eclipse IDE.
4. Ajuste as credenciais do banco em `EmpDao.java`.
5. Execute em um servidor Apache Tomcat 9.0 ou superior.

---
*Este projeto foi realizado por Matheus Correia de França e Davi Leite Coelho para a matéria de Sistemas Web 1.*
