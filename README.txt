# PlayVault - Pacote completo (Pessoa 1 + Login com Room)

Stack (compatível com Android Studio Narwhal e Otter):
- Gradle 8.7 (wrapper)
- AGP 8.6.1
- Kotlin 2.0.21 + plugin compose 2.0.21
- Compose Material3 + Navigation
- Room 2.6.1 (login local)

## Como instalar
1. Faça backup do seu projeto atual.
2. Extraia este zip. Copie **toda a pasta** para o local de sua preferência.
3. Abra pelo Android Studio: **Open** → selecione a pasta **PlayVault-Full**.
4. Garanta em *Settings → Gradle*:
   - **Gradle user home**: `C:\Users\seuUsuario\.gradle`
   - **Gradle JDK**: *Embedded JDK* (\Android\Android Studio\jbr)
   - **Distribution**: *Wrapper*
5. Sincronize (Sync Project).

> Se você já tem outro projeto PlayVault, pode copiar apenas estes itens:
> - `settings.gradle`, `app/build.gradle`, `gradle/wrapper/gradle-wrapper.properties`
> - `app/src/main/**` inteiro (Manifest, java/com/playvault/**, res/values/**)

## O que vem pronto
- Navegação com **BottomBar** (Loja, Biblioteca, Amigos, Perfil)
- Rotas secundárias: Login, Cadastro, Detalhe, Admin
- **Design System** simples (cores, shapes, tipografia)
- **Room** com usuário demo `demo@playvault.app / 123456`
- **Cadastro** grava no Room; **Login** valida no Room

## Teste rápido
- Rode o app → vá em **Perfil** → **Login**
- Use o usuário demo ou cadastre um novo

Se precisar mudar versões, edite: `app/build.gradle` (plugins + deps).
