<h1 align="center">🎮 PlayVault</h1>

<p align="center">
  <em>Aplicativo Android em Kotlin com Jetpack Compose, Navigation e Room Database</em>  
</p>

---

## 📱 Sobre o projeto

O **PlayVault** é um app desenvolvido em Kotlin que permite aos usuários **explorar, salvar e gerenciar jogos** em uma interface moderna e responsiva.  
Ele foi projetado para demonstrar boas práticas de **arquitetura MVVM**, uso do **Room** como banco de dados local e **Navigation Component** para transições entre telas.

---

## 🧩 Funcionalidades

- 🔐 **Autenticação local** com Room (Login e Cadastro)
- 🛒 **Loja** — lista e pesquisa de jogos disponíveis  
- 📚 **Biblioteca** — gerenciamento de jogos adicionados pelo usuário  
- 👥 **Amigos** — simulação de rede social com feedback visual  
- ⚙️ **Admin** — seção especial para gerenciamento (mock funcional)
- 👤 **Perfil** — exibe dados do usuário e permite logout  
- 🎨 **Interface responsiva** com **Material 3 + Jetpack Compose**

---

## 🏗️ Estrutura do projeto

```bash
app/
 ├── data/
 │   ├── dao/         → Data Access Objects (UserDao)
 │   ├── db/          → Banco de dados local (AppDatabase)
 │   ├── entity/      → Entidades (User)
 │   └── repo/        → Repositórios (AuthRepository)
 │
 ├── navigation/
 │   ├── AppNavGraph.kt   → Rotas principais
 │   ├── BottomBar.kt     → Navegação inferior
 │   ├── Routes.kt        → Constantes de rotas
 │   └── Scaffold.kt      → Estrutura base
 │
 ├── ui/
 │   ├── screens/         → Telas (LoginCadastro, Loja, Biblioteca, Amigos, Perfil, Admin)
 │   └── design/          → Tema, Cores, Tipografia e Formas
 │
 └── MainActivity.kt      → Entrada principal do app

## 🧠 Arquitetura

O projeto segue o padrão **MVVM (Model - View - ViewModel)**:
- **Model:** `entity`, `dao`, `repo`
- **View:** Telas Compose em `ui/screens`
- **ViewModel:** Controla estado e fluxo entre UI e banco local

---

## 🚀 Como rodar o projeto

1️⃣ Clone o repositório:
```bash
git clone https://github.com/biavoitechen/PlayVault.git
2️⃣ Abra no Android Studio Otter
3️⃣ Aguarde o Gradle Sync
4️⃣ Rode o app com Run ▶️ (em um emulador ou celular físico)

| Integrante  | Função                            |
| ----------- | --------------------------------- |
| **Bianca**  | UI/UX Design, Login & Navegação   |
| **Ana**     | Interface de Loja e Biblioteca    |
| **Raphael** | Banco de Dados e Lógica Interna   |
| **Mell**    | Painel Admin e Controle de Sessão |

🏁 Status do projeto

🚧 Em desenvolvimento — novas features serão adicionadas nas próximas versões.
