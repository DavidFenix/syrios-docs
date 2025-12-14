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

Este fluxo é equivalente ao formulário Blade:

professor.ofertas.ocorrencias.store

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
  - `List<Long> alunosSelecionados`

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

## 5. Criar Ocorrência (Nova Tela)

### Activity

CriarOcorrenciaActivity

Esta tela é o **espelho direto** do Blade do SyriosWeb.

---

## 6. Estrutura da Tela Criar Ocorrência

A tela é composta por **4 blocos principais**, todos dentro de um `ScrollView`.

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
- `RecyclerView`
- Adapter: `AlunosSelecionadosAdapter`
- Sem checkbox
- Apenas informativo

---

### 🔹 Bloco 2 — Motivos da Ocorrência

Equivalente ao **Accordion do Bootstrap** no Blade.

#### Origem dos Dados

GET /mobile/ocorrencias/motivos

#### Estrutura do DTO
```java
MotivoOcorrencia {
    Long id;
    String descricao;
    String categoria;
}
```
#### Comportamento dos Motivos na UI
- Motivos são agrupados por `categoria`
- Cada motivo possui checkbox
- O professor pode marcar **vários motivos**
- Nenhum motivo é obrigatório

#### UI (decisão inicial)
- `RecyclerView` simples
- Título da categoria
- Lista de checkboxes abaixo
- Accordion (expandir/recolher) pode ser implementado futuramente

---

### 🔹 Bloco 3 — Campos do Formulário

Este bloco espelha exatamente os campos do SyriosWeb.

#### Campos
- **Descrição livre**
  - `EditText`
  - Opcional

- **Local**
  - `Spinner`
  - Opções:
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

Todos os campos ficam dentro de um `ScrollView`.

---

### 🔹 Bloco 4 — Ações

- Botão **Voltar**
- Botão **Registrar Ocorrência**
  - Valida dados
  - Envia ocorrência para o backend

---

## 7. Envio da Ocorrência (Contrato com Backend)

O mobile envia um payload equivalente ao formulário do SyriosWeb:
```json
{
  "oferta_id": 12,
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

- A ocorrência é sempre vinculada à **oferta**
- Os alunos são enviados **explicitamente** (lista de IDs)
- Os motivos são opcionais
- Campos livres são opcionais
- O backend é responsável pela validação final

---

## 8. Regras Importantes (Não Quebrar)

- ❌ Não duplicar regras de negócio no mobile
- ❌ Não inferir turma, aluno ou contexto automaticamente
- ❌ Não assumir valores padrão no app
- ✅ Tudo deve ser enviado de forma explícita
- ✅ O backend é a fonte da verdade
- ✅ O mobile apenas replica o fluxo validado do Web

---

## 9. Próximos Passos Planejados

1. Criar layout XML da `CriarOcorrenciaActivity`
2. Criar adapter de **Alunos Selecionados**
3. Consumir **motivos da ocorrência** via API
4. Implementar seleção de motivos
5. Montar request da ocorrência
6. Registrar ocorrência no backend
7. (Opcional) Implementar accordion expansível
8. (Futuro) Implementar foto real dos alunos

---

## 10. Observação Final

Este documento define o **contrato mental do SyriosMobile** para o fluxo de ocorrências.

Qualquer mudança futura deve respeitar este fluxo para evitar:

- divergência entre Web e Mobile  
- regressões funcionais  
- duplicação de regras de negócio

