Acesse:
https://davidfenix.github.io/syrios-docs/SyriosMobile-Brain.md
Diga ao chatgpt:GPT, consulte o SyriosMobile-Brain
# 🧠 SyriosMobile-Brain

## Fluxo de Alunos, Avatar e Ocorrências (Professor)

---

## 1. Contexto Geral

O **SyriosMobile** é uma versão reduzida do **SyriosWeb (Laravel)**, mantendo **as mesmas regras de negócio**, apenas adaptadas para a experiência mobile.

Toda funcionalidade do mobile **espelha fluxos já validados no SyriosWeb**, sem duplicar lógica no app.  
O **backend continua sendo a fonte da verdade**.

---

## 2. Fluxo Atual do Professor (Resumo)

1. Professor acessa suas **Ofertas**
2. Seleciona uma **Oferta**
3. Visualiza os **Alunos da Turma**
4. Marca um ou mais alunos (checkbox)
5. Clica em **Continuar**
6. Vai para a tela **Criar Ocorrência**
7. Preenche dados e registra a ocorrência
8. Após salvar, o app segue para **Minhas Ocorrências** (feedback visual de sincronização)

Este fluxo é equivalente ao formulário Blade:

`professor.ofertas.ocorrencias.store`

---

## 3. Seleção de Alunos (Implementado)

### Tela
- `SelecionarAlunosActivity`

### Comportamento
- Lista alunos da oferta
- Cada aluno possui:
  - Avatar padrão
  - Nome
  - Matrícula
  - Turma
  - Checkbox
- O professor pode marcar **1 ou mais alunos**
- Botão **Continuar** envia:
  - `oferta_id`
  - `alunos_ids` (IDs dos alunos selecionados)

### Contrato do Intent (ATUAL)
- Para evitar crash de `Parcel` (objetos não Parcelable), o app **não envia objetos** via Intent.
- Envia apenas IDs.

```java
// SelecionarAlunosActivity -> CriarOcorrenciaActivity
long[] alunosIds = new long[selecionados.size()];
for (int i = 0; i < selecionados.size(); i++) alunosIds[i] = selecionados.get(i);

Intent it = new Intent(this, CriarOcorrenciaActivity.class);
it.putExtra("oferta_id", ofertaId);
it.putExtra("alunos_ids", alunosIds);
startActivity(it);
```

### Leitura do Intent (ATUAL)
```java
private void readIntent() {
    ofertaId = -1L;
    alunosSelecionadosIds = new ArrayList<>();

    if (getIntent() == null) return;

    ofertaId = getIntent().getLongExtra("oferta_id", -1L);

    long[] alunosArray = getIntent().getLongArrayExtra("alunos_ids");
    if (alunosArray != null) {
        for (long id : alunosArray) alunosSelecionadosIds.add(id);
    }
}
```

> Observação: `getLongArrayListExtra()` pode ficar indisponível dependendo do nível de API / assinatura usada.
> Por isso, padronizamos em `long[]`.

---

## 4. Avatar do Aluno (Padrão + Zoom)

### Estado Atual
- ❌ Não existe foto real ainda
- ✅ Usa-se um **avatar padrão** (`ic_avatar_aluno`)
- O avatar é **clicável**
- Ao clicar, abre um **zoom em tela cheia**

### Implementação

#### Layout do Item
- Avatar circular
- Fundo `bg_circle_light`
- Ícone centralizado
- Clicável

#### Zoom
- Implementado via `Dialog` fullscreen
- Layout: `dialog_avatar_zoom.xml`
- Fecha ao clicar na imagem

### Decisão Arquitetural
- ❌ Não usar Glide agora
- ❌ Não usar `fotoUrl` agora
- ✅ Estrutura preparada para foto real no futuro

---

## 5. Criar Ocorrência (Tela Principal)

### Activity
- `CriarOcorrenciaActivity`

Esta tela é o **espelho direto** do Blade do SyriosWeb.

---

## 6. Estrutura da Tela Criar Ocorrência

A tela é composta por **4 blocos principais**, dentro de um scroll que suporta conteúdo dinâmico.

### ✅ Importante: Scroll correto para listas dinâmicas (SOLUÇÃO FINAL)

Quando há formulários longos + RecyclerViews que expandem/recolhem (ex.: accordion),
o layout correto é:

- `NestedScrollView` como contêiner do formulário inteiro
- `RecyclerView` com:
  - `layout_height="wrap_content"`
  - `nestedScrollingEnabled="false"`

Isso evita “itens ocultos” e garante recalcular altura ao expandir categorias.

---

### 🔹 Bloco 1 — Alunos Selecionados

Equivalente ao card:

> 👥 Alunos Selecionados

#### Conteúdo
- Lista dos alunos marcados na tela anterior
- Cada aluno mostra:
  - Avatar (padrão, clicável com zoom)
  - Nome
  - Matrícula
  - Turma

#### Implementação
- `RecyclerView` (`rvAlunosSelecionados`)
- Adapter: `AlunosSelecionadosAdapter`
- Sem checkbox (somente leitura)

#### Observação importante (Reaproveitamento)
- Preferir reutilizar os dados já buscados na tela anterior.
- Como o Intent manda apenas IDs, o “reuso” pode ser feito via:
  - **cache local em memória** (ex.: singleton simples) OU
  - **refetch via API** `GET /mobile/oferta/{id}/alunos` e filtrar pelos IDs selecionados.

> No estado atual, o mais simples/seguro é: buscar alunos da oferta e filtrar,
> porque garante consistência com o backend (fonte da verdade).

---

### 🔹 Bloco 2 — Motivos da Ocorrência (Accordion)

Equivalente ao **Accordion do Bootstrap** no Blade.

#### Origem dos Dados
`GET /mobile/ocorrencias/motivos`

#### Estrutura do DTO
```java
MotivoOcorrencia {
    Long id;
    String descricao;
    String categoria;
}
```

#### Comportamento dos Motivos na UI (ATUAL)
- Motivos agrupados por `categoria`
- Categorias são clicáveis (expandir/recolher)
- Cada motivo tem checkbox
- O professor pode marcar **vários motivos**
- Nenhum motivo é obrigatório
- Botão “Expandir todos / Recolher todos” funciona

#### Implementação
- `RecyclerView` (`rvMotivos`)
- Adapter: `MotivosAdapter`
- Estratégia: lista completa -> lista visível (rebuild) com base no estado `expandida`

---

### 🔹 Bloco 3 — Campos do Formulário

Este bloco espelha exatamente os campos do SyriosWeb.

#### Campos
- **Descrição livre**
  - `EditText`
  - Opcional

- **Local**
  - `Spinner`
  - Opções (exemplo):
    - Sala de aula
    - Ambientes de apoio
    - Pátio da escola
    - Quadra poliesportiva
    - Galerias
    - Outro

- **Atitude do professor**
  - `Spinner`
  - Opções:
    - Advertência
    - Ordem de saída de sala
    - Outra

- **Outra atitude**
  - `EditText`
  - Opcional

- **Comportamento**
  - `Spinner`
  - Opções:
    - 1ª vez
    - Reincidente (pouco frequente)
    - Reincidente (frequente)

- **Sugestão de medidas**
  - `EditText`
  - Opcional

---

### 🔹 Bloco 4 — Ações
- Botão **Voltar**
- Botão **Registrar Ocorrência**
  - Valida dados
  - Envia ocorrência para o backend
  - Em caso de sucesso, navega para “Minhas Ocorrências” e mostra feedback de sincronização

---

## 7. Envio da Ocorrência (Contrato com Backend)

O mobile envia um payload equivalente ao formulário do SyriosWeb:

```json
{
  "alunos": [3, 5, 9],
  "motivos": [1, 4, 7],
  "descricao": "Descrição livre",
  "local": "Sala de aula",
  "atitude": "Advertência",
  "outra_atitude": "",
  "comportamento": "1ª vez",
  "sugestao": "Encaminhar à direção"
}
```

### Regras de envio
- A ocorrência é vinculada à **oferta** pela rota: `POST /mobile/ofertas/{oferta}/ocorrencias`
- Os alunos são enviados **explicitamente** (lista de IDs)
- Os motivos são opcionais
- Campos livres são opcionais
- O backend valida e grava em `syrios_ocorrencia` e `syrios_ocorrencia_motivo`

---

## 8. Backend Mobile (Autenticação + Contexto)

### Middleware `mobile.auth` (ATUAL)
- Autentica via header:
  - `Authorization: Bearer <token>`
- Token é base64 e contém prefixo `TOKEN_MOBILE_{id}`.
- Extrai contexto via headers:
  - `X-SCHOOL-ID`
  - `X-ROLE`

### ✅ Sessão do Laravel (Web e Mobile)
Para compatibilizar os **scopes** existentes (ex.: `ModeloMotivo::daEscolaAtual()`),
o middleware injeta o contexto do mobile na **session** do request atual:

```php
if ($schoolId) session(['current_school_id' => (int) $schoolId]);
if ($role)     session(['current_role' => $role]);
```

> Isso não “mistura” sessões de usuários diferentes: cada request tem seu próprio cookie/contexto.
> Para APIs, o essencial é garantir que o request tenha a session configurada antes de usar scopes.

---

## 9. Store Mobile (Salvar ocorrência no SyriosWeb)

### Controller
- `MobileOcorrenciaController@store`

### Regras críticas (para não quebrar o banco)
- `syrios_ocorrencia.school_id` **não tem default** → deve ser preenchido.
- `professor_id` deve ser o ID da tabela `syrios_professor` (não `syrios_usuario`).
- Uma ocorrência é criada **para cada aluno** selecionado (1..N), igual ao Web.

### Modelo de gravação (equivalente ao Web)
- Preenche:
  - `school_id`, `ano_letivo`, `vigente`
  - `aluno_id`, `professor_id`, `oferta_id`
  - campos de formulário
  - `nivel_gravidade` padrão

- Motivos:
  - `$ocorrencia->motivos()->syncWithoutDetaching($idsMotivos);`

---

## 10. Sincronização (UX) — Feedback permanente

### Situação atual
O backend grava imediatamente (online), então “sincronizar” aqui significa:
- ✅ “Foi salvo no servidor agora”

### Objetivo de UI
Em vez de `Toast`, usar um banner fixo no topo (estilo Bootstrap alert):
- “✅ Sincronizado agora mesmo”
- “✅ Sincronizado há 2 min”
- Tudo em **português**

### Onde exibir
- `MinhasOcorrenciasActivity` (tela de listagem)

### Layout sugerido (já criado)
- `alertSync` (container)
- `txtAlertSync` (texto)
- `btnCloseAlert` (fechar)

### Como alimentar
- Ao salvar ocorrência com sucesso, navegar para `MinhasOcorrenciasActivity` passando:
  - timestamp atual (ex.: `System.currentTimeMillis()`)
- Na `MinhasOcorrenciasActivity`, calcular texto:
  - “Sincronizado agora mesmo” (<= 30s)
  - “Sincronizado há X min” (>= 1 min)

---

## 11. Problemas que já apareceram (para evitar regressão)

### 11.1 Crash ao mandar objeto no Intent (Parcel)
Erro: `Parcel: unknown type for value ... AlunoDaOfertaRemote...`

✅ Solução: mandar apenas IDs (`long[]`), não objetos.

### 11.2 Itens ocultos ao expandir motivos
✅ Solução final:
- `NestedScrollView`
- RecyclerViews com `wrap_content` e `nestedScrollingEnabled="false"`

### 11.3 Falta de `school_id` ao gravar ocorrência
Erro SQL: `Field 'school_id' doesn't have a default value`

✅ Solução: preencher `school_id` e demais campos obrigatórios na criação.

---

## 12. Regras Importantes (Não Quebrar)

- ❌ Não duplicar regras de negócio no mobile
- ❌ Não inferir turma, aluno ou contexto automaticamente
- ❌ Não assumir valores mágicos no app
- ✅ Tudo deve ser enviado de forma explícita
- ✅ O backend é a fonte da verdade
- ✅ O mobile apenas replica o fluxo validado do Web
- ✅ Sempre respeitar o prefixo dinâmico (`prefix()` / BaseModel)

---

## 13. Próximos Passos Planejados

1. ✅ Registrar ocorrência no backend (feito)
2. ✅ Motivos por escola (feito via scope + session no middleware)
3. ✅ Accordion (expandir/recolher) (feito)
4. ✅ Mostrar alunos selecionados (feito com adapter + IDs)
5. ⏭️ Pós-salvar: redirecionar para Minhas Ocorrências (ajuste de navegação)
6. ⏭️ Banner “Sincronizado” persistente (UI + cálculo de tempo)
7. ⏭️ Tela de detalhe da ocorrência (com motivos e dados completos)
8. (Futuro) Foto real do aluno + cache / Glide
9. (Futuro) Suporte offline / fila de sincronização (se necessário)

---

## 14. Observação Final

Este documento define o **contrato mental do SyriosMobile** para o fluxo de ocorrências.

Qualquer mudança futura deve respeitar este fluxo para evitar:

- divergência entre Web e Mobile  
- regressões funcionais  
- duplicação de regras de negócio
