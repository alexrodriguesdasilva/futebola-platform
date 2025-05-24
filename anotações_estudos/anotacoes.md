
# 🧠 Anotações sobre o Projeto Java Spring Boot - Futebola Platform

## 📌 Objetivo do Projeto

Criar uma aplicação web para gerenciar **jogadores de futebol amador**, com funcionalidades para cadastrar e listar jogadores.

---

## 🛠️ Tecnologias Usadas

- **Java 21**: linguagem principal do projeto.
- **Spring Boot**: framework que facilita o desenvolvimento de aplicações web em Java.
- **PostgreSQL**: banco de dados relacional para armazenar as informações dos jogadores.
- **Maven** (ou Gradle): gerenciador de dependências do projeto.
- **Lombok**: biblioteca que evita código repetitivo.
- **JPA (Jakarta Persistence API)**: conecta Java ao banco de dados de forma fácil.

---

## 🧱 Conceitos Importantes

### 🧩 O que é Spring Boot?

Spring Boot é um **framework Java** que ajuda a criar aplicações web de forma rápida e simples. Ele já vem com várias configurações prontas (opiniadas).

### 🛠️ O que é um CRUD?

CRUD é um conjunto de operações básicas usadas em banco de dados:

- **C**: Create → Criar dados (ex: cadastrar jogador)
- **R**: Read → Ler dados (ex: listar jogadores)
- **U**: Update → Atualizar dados
- **D**: Delete → Deletar dados

No código atual, usamos:
- **Create** (via `POST /jogador`)
- **Read** (via `GET /jogador`)

### 🗃️ O que é um banco de dados?

Um **banco de dados** é onde os dados da aplicação são armazenados. No nosso projeto usamos o **PostgreSQL**, um banco relacional (estrutura de tabelas, tipo planilhas).

---

## 🔧 Configurações do Banco de Dados

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/futebola_db
spring.datasource.username=postgres
spring.datasource.password=123456
spring.jpa.hibernate.ddl-auto=update
```

---

## 🚀 Explicando o Código

### 1. Classe Principal

```java
@SpringBootApplication
public class FutebolaPlatformApplication {
    public static void main(String[] args) {
        SpringApplication.run(FutebolaPlatformApplication.class, args);
    }
}
```

Essa é a **classe principal**. A aplicação começa aqui.

---

### 2. Controlador (Controller)

```java
@RestController
@RequestMapping("jogador")
public class JogadorController {
    @Autowired
    private JogadorRepository repository;

    @PostMapping
    public void saveJogador(@RequestBody JogadorRequestDTO data) {
        Jogador jogadorData = new Jogador(data);
        repository.save(jogadorData);
    }

    @GetMapping
    public List<JogadorResponseDTO> getAll() {
        return repository.findAll().stream().map(JogadorResponseDTO::new).toList();
    }
}
```

Controla as requisições HTTP: cadastrar e listar jogadores.

---

### 3. Modelo (Model)

```java
@Entity(name = "jogador")
@Table(name = "jogador")
public class Jogador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String apelido;
    private String telefone;
    private Integer idade;

    public Jogador(JogadorRequestDTO data) {
        this.nome = data.nome();
        this.apelido = data.apelido();
        this.telefone = data.telefone();
        this.idade = data.idade();
    }
}
```

Define a estrutura da tabela no banco de dados.

---

### 4. Repositório

```java
public interface JogadorRepository extends JpaRepository<Jogador, Long> {}
```

Responsável pela comunicação com o banco.

---

### 5. DTOs

```java
public record JogadorRequestDTO(String nome, String apelido, String telefone, Integer idade) {}
```

Usado para receber os dados.

```java
public record JogadorResponseDTO(Long id, String nome, String apelido, String telefone, Integer idade) {
    public JogadorResponseDTO(Jogador jogador){
        this(jogador.getId(), jogador.getNome(), jogador.getApelido(), jogador.getTelefone(), jogador.getIdade());
    }
}
```

Usado para devolver os dados formatados.

---

## 🧠 Extras para Estudar

- **Orientação a Objetos (OO)**: programar com "objetos" (como Jogador).
- **DTOs**: ajudam na segurança e organização dos dados.
- **Banco Relacional**: estrutura em tabelas.

---

## ✅ Próximos Passos

- Criar funcionalidades de **atualizar** e **deletar** jogadores.
- Adicionar validações com `@Valid`.
- Usar ferramentas como **Postman** para testar a API.
