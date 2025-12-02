# 🚀 Missões Lunares

Missões Lunares é um sistema desenvolvido em Java para gerenciar missões espaciais da agência fictícia **Lunar Systems**, permitindo registrar astronautas, naves e missões, além de organizar seus relacionamentos.

## 🧠 Sobre o projeto
O sistema permite:
- Cadastrar **missões**, **astronautas** e **naves**;
- Relacionar cada missão à nave utilizada e aos astronautas participantes;
- Registrar datas de lançamento e retorno;
- Validar regras de negócio, como idade mínima e código único;
- Persistir dados através de arquivos, utilizando repositórios organizados em camadas;
- Listar informações e consultar registros pelo menu principal.

## ⚙️ Requisitos
Antes de executar o projeto, é necessário ter instalado:
- **Java JDK 17+**
- **Git**
- Uma IDE compatível com Maven (VSCode, IntelliJ ou Eclipse)

O projeto utiliza apenas bibliotecas padrão do Java, sem necessidade de arquivos `.jar` externos.

## 🚀 Como executar
1. Baixe ou clone o repositório.  
2. Abra o projeto em uma IDE com suporte a Maven.  
3. Compile o projeto normalmente.  
4. Execute a classe principal:

ou

1. Baixe ou clone o repositório.  
2. Compile com esta linha de comando no terminal:
```
mvn clean compile
```
4. E então execute com esta linha de comando: mvn exec:java -Dexec.mainClass="rv.missoes_lunares.view.Main"
```
mvn exec:java -Dexec.mainClass="rv.missoes_lunares.view.Main"
```

```
src/main/java/rv/missoes_lunares/view/Main.java
```

O programa será iniciado no terminal e exibirá o menu principal do sistema.

## 🧾 Estrutura básica
```
MissõesLunares/
├── src/
│   └── main/java/rv/missoes_lunares/
│       ├── model/           # Entidades: Missão, Astronauta, Nave e relacionamentos
│       ├── repository/      # Persistência, leitura e gravação dos dados
│       ├── service/         # Regras de negócio e utilidades
│       └── view/            # Interface textual e classe Main
├── pom.xml                  # Configuração Maven
└── README.md
```

## 👨‍💻 Autor
Desenvolvido por **Gustavo Desordi** como projeto acadêmico de Programação Orientada a Objetos.

---

<p align="center">
  <img src="https://media4.giphy.com/media/v1.Y2lkPTZjMDliOTUydGx6MXkydnJ2NWx2OGlkaGt3cjd4cWYzajhya3NmMjU4aTg5MzlzaSZlcD12MV9naWZzX3NlYXJjaCZjdD1n/tRoH9EYLs3lok/200w.gif" alt="Cute cat" width="300" />
</p>

