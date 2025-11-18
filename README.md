PlayVault — Gestão de Jogos com Jetpack Compose + MVVM + Room

<p align="center">
  <img src="https://img.shields.io/badge/Kotlin-1.9%2B-purple?style=for-the-badge&logo=kotlin"/>
  <img src="https://img.shields.io/badge/Android-Jetpack%20Compose-3DDC84?style=for-the-badge&logo=android"/>
  <img src="https://img.shields.io/badge/Architecture-MVVM-blue?style=for-the-badge"/>
  <img src="https://img.shields.io/badge/Database-Room-orange?style=for-the-badge"/>
</p>O PlayVault é um aplicativo Android desenvolvido como projeto final da disciplina.
O app funciona como um organizador de jogos, com navegação completa, tela de detalhes, loja e estrutura pronta para CRUD completo usando Room.

Este projeto segue boas práticas modernas de desenvolvimento Android:

✔ Jetpack Compose
✔ Navegação por NavHost
✔ MVVM com StateFlow
✔ Camadas bem separadas (UI, ViewModel, Repository, DataSource)
✔ Preparado para Room Database e integrações externas


---

📱 Funcionalidades atuais

✔ Loja de jogos

Exibe tela inicial da loja

Botão "Ver detalhes" funcionando

Navegação para a tela de detalhes


✔ Tela de Detalhes

Recebe itemId como argumento

Exibe nome, descrição e informações do jogo


✔ Navegação completa funcionando

Loja

Detalhes

Perfil

Biblioteca

Amigos

Admin (layout inicial)

Login (layout inicial)


✔ Design System

Tema Material 3

Cores unificadas

Tipografia consistente

Componentes base (ScreenScaffold)



---

🧩 Arquitetura do Projeto

O PlayVault segue a arquitetura moderna recomendada pelo Google:

📦 com.playvault
 ┣ 📂 navigation         → NavGraph, rotas, argumentos
 ┣ 📂 ui
 │   ┣ 📂 components     → ScreenScaffold e elementos reutilizáveis
 │   ┣ 📂 screens        → Loja, Login, Perfil, Biblioteca, Detalhes, Admin
 ┣ 📂 viewmodel          → ViewModels com StateFlow
 ┣ 📂 data               → (Em implementação: Room, DAOs, Entities)
 │   ┣ 📂 repo           → Repositórios (AuthRepository, StoreRepository etc.)
 ┣ MainActivity.kt       → Ponto de entrada do app

Padrões utilizados:

MVVM com StateFlow

UI State hoisting

Navegação unidirecional de dados

Separação de camadas limpa

Cada tela possui seu ViewModel / UiState



---

👥 Responsabilidades por integrante do grupo

🟣 Bia (Líder de UI / Navegação / Design)

Responsável por:

Criar NavGraph, rotas e navegação global

Criar BottomNavigation

Criar telas:

Login

Cadastro

Perfil


Montar o Design System

Criar componentes base (ScreenScaffold)

Definir UiContracts (rotas, events, interfaces de UI)


🔵 Ana (ViewModels & Lógica de Negócio)

Responsável por:

Criar ViewModels: Store, Biblioteca, Amigos, Admin, Auth

Criar UiState de cada VM

Tratar erros, estados e loading

Aplicar filtros, ordenações e lógica

Integrar dados com telas via StateFlow


🟢 Raphael (Banco de Dados Room / CRUD / Flow)

Responsável por:

Criar entidades Room:

GameEntity

LibraryEntryEntity

FriendEntity

UserPrefEntity


Criar DAOs com Flow

Criar AppDatabase

Implementar CRUD completo

Integrar Biblioteca e Amigos com Room

Seed local e cache


🟡 Mell (Repository / Integração Externa / Admin)

Responsável por:

Criar Repositórios

Integrar fonte externa (Firebase ou Retrofit Mock)

Unificar Room + remoto

Criar CRUD remoto para Admin

Disponibilizar dados para ViewModels



---

🚀 Como rodar o projeto

1. Clone o repositório

git clone https://github.com/SEU-USUARIO/PlayVault.git

2. Abra no Android Studio (Koala / Hedgehog / Iguana)

3. Conecte um celular ou crie um emulador

4. Execute:

Run > Run 'app'


---

🧪 Testado em:

Samsung A52 – Android 14

Emulador Pixel 6 – API 33

Emulador Pixel 4A – API 34



---

🛠️ Tecnologias

Kotlin

Jetpack Compose

Material 3

Navigation Compose

MVVM

StateFlow

Room Database

Coroutines

Firebase (Admin)



---

📌 Roadmap das próximas versões

🔜 Versão 2.0

CRUD completo de Biblioteca usando Room

CRUD completo de Amigos

Autenticação real

Tela de Admin funcional

Catálogo real de jogos


🔜 Versão 3.0

Layout final com componentes 100% personalizados

Carrosséis, cards animados, filtros avançados

Login social



---

📝 Licença

Projeto acadêmico — uso livre para estudo.
