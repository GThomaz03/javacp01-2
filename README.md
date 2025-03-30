🎉 **Bem-vindo ao TranquiloTech** 🚀

## Integrantes 
RM554945 - NATHÁLIA GOMES DA SILVA
RM558637 - GABRIEL ALVES THOMAZ

## ℹ️ Sobre o Projeto

O TranquiloTech é um sistema de gerenciamento de funcionários que utiliza JPA (Java Persistence API) para gerenciar operações CRUD (Criar, Ler, Atualizar, Deletar) em um banco de dados. A aplicação foi estruturada com herança, utilizando as classes Funcionario, FuncionarioEstagiario e FuncionarioSenior, para representar diferentes tipos de funcionários na empresa.

## 💡 Principais Funcionalidades

1. **Cadastro de funcionários (estagiários e seniores)**

2. **Visualização de informações dos funcionários**

3. **Edição e remoção de funcionários do banco de dados**

4. **Sistema de herança, permitindo diferenciação entre os tipos de funcionário**


## 🛠️ Tecnologias e Ferramentas Utilizadas

Este projeto utiliza as seguintes tecnologias e ferramentas:


- Java 11 (ou superior)

- JPA (Java Persistence API) para manipulação de banco de dados

- Hibernate como implementação de JPA

- MySQL ou PostgreSQL como banco de dados relacional

- Maven para gerenciamento de dependências e build do projeto


## 🚀 Executando o Projeto

Siga as instruções abaixo para executar o projeto em seu ambiente local:


1. **Clone o repositório:**

   ```
   git clone https://github.com/GThomaz03/javacp01-2.git
   ```

2. **Navegue até o diretório do projeto:**

   ```
   cd javacp01-2
   ```

3. **Instale as dependências:**

   ```
   pip install -r requirements.txt
   ```

4. **Execute o servidor:**

   ```
   python app.py
   ```

## 📘 Estrutura de Classes

1. Funcionario

Atributos:

	id: Identificador único do funcionário (Auto increment).

	nome: Nome do funcionário.

	cargo: Cargo do funcionário (Estagiário, Senior, etc.).

	salario: Salário do funcionário.


2. FuncionarioEstagiario (Herda de Funcionario)

Representa um tipo específico de funcionário com características adicionais ou comportamentos diferenciados, se necessário.


3. FuncionarioSenior (Herda de Funcionario)
Representa um tipo específico de funcionário mais experiente ou com maior responsabilidade.
