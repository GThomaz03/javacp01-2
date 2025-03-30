🎉 **Bem-vindo ao TranquiloTech** 🚀

## Integrantes 
RM554945 - NATHÁLIA GOMES DA SILVA

RM558637 - GABRIEL ALVES THOMAZ

## 1. ℹ️ Sobre o Projeto

O TranquiloTech é um sistema de gerenciamento de funcionários que utiliza JPA (Java Persistence API) para gerenciar operações CRUD (Criar, Ler, Atualizar, Deletar) em um banco de dados. A aplicação foi estruturada com herança, utilizando as classes Funcionario, FuncionarioEstagiario e FuncionarioSenior, para representar diferentes tipos de funcionários na empresa.

## 💡 Principais Funcionalidades

1. **Cadastro de funcionários (estagiários e seniores)**

2. **Visualização de informações dos funcionários**

3. **Edição e remoção de funcionários do banco de dados**

4. **Sistema de herança, permitindo diferenciação entre os tipos de funcionário**


## 2. 🛠️ Tecnologias e Ferramentas Utilizadas

Este projeto utiliza as seguintes tecnologias e ferramentas:


- Java 17

- JPA / Hibernate

- Banco de Dados Oracle

- Maven

- JDK / JDBC

- API Reflection

## 🚀 Executando o Projeto

Siga as instruções abaixo para executar o projeto:


1. **Clone o repositório:**

   ```
   git clone https://github.com/GThomaz03/javacp01-2.git
   ```

2. **Navegue até o executável do projeto:**

   ```
   cd javacp01-2/src/main/java/br/com/fiap/Main.java
   ```

3. **Execute o arquivo:**

   ```
   Main.java
   ```


## 3. 📂 Estrutura do Projeto

   ```
   📦 src/main/java/br/com/fiap
    ┣ 📂 dao
    ┃ ┣ 📜 FuncionarioDao.java
    ┃ ┣ 📜 FuncionarioDaoImpl.java
    ┣ 📂 entity
    ┃ ┣ 📜 Funcionario.java
    ┃ ┣ 📜 FuncionarioSenior.java
    ┃ ┣ 📜 FuncionarioEstagiario.java
    ┣ 📂 exceptions
    ┃ ┣ 📜 HorasTrabalahdasInvalidas.java
    ┃ ┣ 📜 ValorInvalido.java
    ┣ 📂 util
    ┃ ┣ 📜 GeradorSQL.java
    ┣ 📜 Main.java
    ┣ 📜 README.md
   ```
📁 br.com.fiap.dao (Camada de Acesso a Dados)
  - FuncionarioDao: Interface que define os métodos para manipulação dos dados dos funcionários.
  - FuncionarioDaoImpl: Implementação da interface, utilizando JPA/Hibernate para realizar as operações no banco de dados.
 
📁 br.com.fiap.entity (Modelos de Dados)
  - Funcionario: Classe base representando um funcionário genérico.
  - FuncionarioSenior: Extensão de Funcionario que inclui bônus salariais.
  - FuncionarioEstagiario: Extensão de Funcionario que aplica desconto no salário.
  
📁 br.com.fiap.util (Utilitários)
  - GeradorSQL: Classe que gera comandos SQL dinamicamente utilizando Reflection.

📁 br.com.fiap (Executável)
  - Main: Classe principal que executa testes no sistema.


## 4. Funcionamento do Programa
A seguir, detalhamos o funcionamento de cada parte do código.

### 4.1. FuncionarioDaoImpl - Implementação do DAO
Este DAO gerencia todas as operações CRUD no banco de dados.

Exemplo do método salvar() que persiste um funcionário no banco

 ```
@Override
public void salvar(Funcionario funcionario) {
    EntityManager em = emf.createEntityManager();
    try {
        em.getTransaction().begin();
        em.persist(funcionario);
        em.getTransaction().commit();

        // Gera e exibe o SQL do INSERT
        String sqlInsert = GeradorSQL.gerarInsert(funcionario);
        System.out.println("SQL Gerado (Insert): " + sqlInsert);
    } catch (Exception e) {
        em.getTransaction().rollback();
        e.printStackTrace();
    } finally {
        em.close();
    }
}
 ```
O método inicia uma transação.

Persiste o objeto funcionario no banco de dados.

Gera e imprime o comando SQL via GeradorSQL.

### 4.2. GeradorSQL - Geração Dinâmica de SQL
Utilizando Reflection, essa classe analisa as anotações JPA das entidades e gera comandos SQL automaticamente.

Exemplo de método para INSERT:
 ```
public static String gerarInsert(Object objeto) {
    Class<?> classe = objeto.getClass();
    if (!classe.isAnnotationPresent(Table.class)) {
        throw new RuntimeException("A classe não possui a anotação @Table");
    }

    String nomeTabela = classe.getAnnotation(Table.class).name();
    StringBuilder colunas = new StringBuilder();
    StringBuilder valores = new StringBuilder();

    for (Field campo : classe.getDeclaredFields()) {
        if (campo.isAnnotationPresent(Column.class)) {
            campo.setAccessible(true);
            try {
                String nomeColuna = campo.getAnnotation(Column.class).name();
                Object valor = campo.get(objeto);

                if (valor != null) {
                    colunas.append(nomeColuna).append(",");
                    valores.append(valor instanceof String ? "'" + valor + "'" : valor).append(",");
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Erro ao acessar campo", e);
            }
        }
    }

    if (colunas.length() > 0) colunas.setLength(colunas.length() - 1);

    return "INSERT INTO " + nomeTabela + " (" + colunas + ") VALUES (" + valores + ");";
}
```
Lê dinamicamente os campos anotados com @Column nas entidades.

Gera um comando SQL de INSERT formatado corretamente.

### 4.3. Main.java - Testando o Sistema
A classe Main executa testes das funcionalidades do sistema.

Exemplo 1 de execução do CRUD
```
public class Main {
    public static void main(String[] args) {
        FuncionarioDao funcionarioDao = new FuncionarioDaoImpl();

        Funcionario funcionario1 = new Funcionario("Carlos", 40, 50);
        FuncionarioSenior senior1 = new FuncionarioSenior("Ana", 45, 80);
        FuncionarioEstagiario estagiario1 = new FuncionarioEstagiario("Pedro", 30, 30);

        funcionarioDao.salvar(funcionario1);
        funcionarioDao.salvar(senior1);
        funcionarioDao.salvar(estagiario1);
    }
}
```

![saidaInsert](https://github.com/user-attachments/assets/82418459-296b-456c-b1f1-6ee465755a97)

Exemplo 2 de execução do CRUD
```
public class Main {
    public static void main(String[] args) {
        FuncionarioDao funcionarioDao = new FuncionarioDaoImpl();

        List<Funcionario> funcionarios = funcionarioDao.listarTodos();
        funcionarios.forEach(f -> System.out.println(f.imprimirInformacao()));
    }
}
```

![saidaSelect](https://github.com/user-attachments/assets/29bc2297-64f8-45bf-9e2d-62af16d73b07)

## 6. Conclusão
Este projeto demonstrou a implementação de um sistema de gerenciamento de funcionários utilizando JPA com Hibernate, juntamente com a geração dinâmica de SQL via Reflection. O programa oferece um CRUD completo e pode ser facilmente expandido para incluir novas funcionalidades.

📌 Principais aprendizados: ✅ Uso de JPA/Hibernate para persistência.
✅ Aplicação do padrão DAO para desacoplamento.
✅ Geração dinâmica de SQL com Reflection.
