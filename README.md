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

```
src/main/java/rv/missoes_lunares/view/Main.java
```

ou rode as seguintes linhas de comando no Linux:

1. Para compilar:
```
mvn clean compile
```

2. Para executar:
```
mvn exec:java -Dexec.mainClass="rv.missoes_lunares.view.Main"
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
  <img src="https://i.pinimg.com/originals/f0/ae/8b/f0ae8bc86ab9b5459880ea9e8894774d.gif" alt="Cute cat" width="300" />
</p>
