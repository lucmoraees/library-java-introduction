## **Library Management System**

Este projeto é um sistema de gerenciamento de biblioteca desenvolvido em **Java**, com suporte a funcionalidades como cadastro de usuários, empréstimos e devoluções de livros, além de um chatbot interativo no terminal para facilitar a interação do usuário com o sistema.

---

### **Estrutura do Projeto**

A seguir, a descrição de cada pasta e seus conteúdos:

#### **1. `Data`**
Contém os arquivos CSV que armazenam os dados persistentes do sistema:
- `books.csv`: Contém os registros dos livros cadastrados.
- `users.csv`: Contém os registros dos usuários cadastrados.
- `loans.csv`: Contém os registros dos empréstimos realizados.

#### **2. `Entities`**
Contém as classes que representam as entidades principais do sistema:
- `Book`: Representa um livro.
- `Loan`: Representa um empréstimo.
- `User`: Representa um usuário.
- `UserLevel`: Enum que define o nível de acesso do usuário (ADMIN ou MEMBER).

#### **3. `Repositories`**
Contém as interfaces e implementações dos repositórios responsáveis por manipular os dados persistentes:
- `BookRepository`: Interface para operações com livros.
- `LoanRepository`: Interface para operações com empréstimos.
- `UserRepository`: Interface para operações com usuários.
- `impl`: Contém as implementações concretas das interfaces de repositórios.

#### **4. `UseCases`**
Contém as interfaces e implementações dos **casos de uso** do sistema, organizados por tipo:
- **`Books`**: Casos de uso relacionados a livros.
  - `AddBookUseCase`
  - `DeleteBookUseCase`
  - `ListAvailableBooksUseCase`
  - `UpdateLoanDurationUseCase`
- **`Loans`**: Casos de uso relacionados a empréstimos.
  - `BorrowBookUseCase`
  - `GetLoansByUserUseCase`
  - `ReturnBookUseCase`
- **`Users`**: Casos de uso relacionados a usuários.
  - `GetUserByIdUseCase`
  - `GetUserByUsernameUseCase`
  - `RegisterUserUseCase`
  - `CheckIfUserIsAdminUseCase`

Cada caso de uso possui uma interface e uma implementação concreta na subpasta `impl`.

#### **5. `UI`**
Contém as classes responsáveis pela interface de usuário via terminal:
- `LibraryChatBotApp`: Classe principal que autentica o usuário e redireciona para o chatbot apropriado (admin ou não admin).
- `AdminChatBot`: Classe que gerencia as opções disponíveis para administradores.
- `UserChatBot`: Classe que gerencia as opções disponíveis para usuários comuns.

#### **6. `Utils`**
Contém classes utilitárias e de suporte ao sistema:
- `Constants`: Classe que contém constantes utilizadas no projeto.
- `ConvertToCsv`: Classe responsável por conversões entre objetos e linhas CSV.
- `DataSeeder`: Classe responsável por inserir dados iniciais no sistema (seed).
- `DependencyInjector`: Classe responsável pela injeção de dependência dos repositórios e casos de uso.

#### **7. Arquivo `App.java`**
O arquivo principal que inicia a aplicação. Ele executa o **seed** de dados iniciais e, em seguida, inicia o **LibraryChatBotApp** para interação com o usuário.

---

### **Como Executar o Projeto**

1. Certifique-se de ter o **Java Development Kit (JDK)** instalado.
2. Compile o projeto utilizando o comando:
   ```bash
   javac -d out src/main/java/com/library/**/*.java
   ```
3. Navegue até a pasta de saída `out` e execute a aplicação com o comando:
   ```bash
   java com.library.App
   ```

---

### **Funcionalidades**

- **Usuário Comum**:
  - Listar livros disponíveis.
  - Pegar livros emprestados.
  - Devolver livros.
  - Listar seus empréstimos.

- **Admin**:
  - Listar livros disponíveis.
  - Cadastrar novos livros.
  - Alterar o tempo de empréstimo de um livro.
  - Cadastrar novos usuários.
  - Listar usuários cadastrados.
  - Deletar usuários.

---

### **Exemplo de Interação**

```plaintext
Bem-vindo ao sistema de biblioteca!
Informe seu username para iniciar:
> adminuser
Bem-vindo, Admin!

Escolha uma opção:
1. Listar livros disponíveis
2. Cadastrar um novo livro
3. Alterar tempo de empréstimo de um livro
4. Cadastrar um novo usuário
5. Listar usuários cadastrados
6. Deletar usuário
7. Sair
> 2
Informe o título do livro:
> Clean Architecture
Informe a descrição do livro:
> A comprehensive guide to software design principles
Informe o tempo de empréstimo padrão (em dias):
> 30
Livro cadastrado com sucesso!
```

### **Licença**

Este projeto é open-source e pode ser utilizado livremente para fins educacionais e comerciais.

---
