<template>
  <div class="container">
    <div class="two-column-grid">
      <!-- Participantes -->
      <div class="section-box">
        <h1 class="title">Lista de Pessoas e Itens para Divisão</h1>
        <!-- Botões para adicionar e remover participantes -->
        <div class="btn-group">
          <button @click="addParticipant" class="btn-add btn-add-green"><i class="fas fa-user-plus"></i> Pessoa</button>
          <button v-if="participants.length > 2" @click="removeParticipant(participants.length - 1)" class="btn-remove btn-remove-red"><i class="fas fa-user-times"></i> Pessoa</button>
          <button v-else class="btn-remove btn-remove-red" disabled><i class="fas fa-user-times"></i> Pessoa</button>
        </div>
        <!-- Formulário para cada participante -->
        <div v-for="(participant, participantIndex) in participants" :key="participantIndex" class="participant-section">
          <div class="participant-info-box">
            <div class="participant-info">
              <div class="pessoas-itens-box">
              <div class="form-group">
                <input type="text" v-model="participant.name" :placeholder="'Nome da Pessoa '" :id="'participantName' + participantIndex" class="input input-small" />
              </div>
              <div class="item-group" v-for="(item, itemIndex) in participant.items" :key="itemIndex">
                <input type="text" v-model="item.name" :placeholder="'Nome do item '" class="input input-small" />
                <input type="number" v-model="item.value" :placeholder="'Valor do item'" class="input input-small" @input="validateValue(participantIndex, itemIndex)" />
                <div class="btn-group">
                  <button @click="addParticipantItem(participantIndex)" v-if="itemIndex === participant.items.length - 1" class="btn-add btn-add-green"><i class="fas fa-plus"></i> Item</button>
                  <button @click="removeParticipantItem(participantIndex, itemIndex)" :disabled="itemIndex === 0 && participant.items.length === 1" class="btn-remove btn-remove-red"><i class="fas fa-minus"></i> Item</button>
                </div>
              </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <!-- Gastos adicionais -->
      <div class="section-box">
        <h2 class="title">Gastos Adicionais do Grupo</h2>
        <div class="additional-section">
          <div class="additional-expenses-box">
            <div class="additional-expenses">
              <div v-for="(expense, index) in increases" :key="index" class="form-group">
                <input type="text" v-model="expense.name" :placeholder="'Nome do gasto ' + (index + 1)" class="input input-small" />
                <input type="text" v-model="expense.value" :placeholder="'Valor do gasto' + (index + 1)" class="input input-small" />
                <select v-model="expense.isPercentage" class="input input-small">
                  <option value="true">Porcentagem</option>
                  <option value="false">Valor Fixo</option>
                </select>
                <div class="btn-group">
                  <button @click="addExpense" v-if="index === increases.length - 1" class="btn-add btn-add-green"><i class="fas fa-plus"></i> Gasto</button>
                  <button @click="removeAdditionalExpense(index)" :disabled="index === 0 && increases.length === 1" class="btn-remove btn-remove-red"><i class="fas fa-minus"></i> Gasto</button>
                </div>
              </div>
            </div>
          </div>
        </div>
      <!-- Descontos adicionais -->
        <h2 class="title">Descontos Adicionais do Grupo</h2>
        <div class="additional-section">
          <div class="additional-discounts-box">
            <div class="additional-discounts">
              <div v-for="(discount, index) in discounts" :key="index" class="form-group">
                <input type="text" v-model="discount.name" :placeholder="'Nome do desconto ' + (index + 1)" class="input input-small" />
                <input type="text" v-model="discount.value" :placeholder="'Valor do desconto' + (index + 1)" class="input input-small" />
                <select v-model="discount.isPercentage" class="input input-small">
                  <option value="true">Porcentagem</option>
                  <option value="false">Valor Fixo</option>
                </select>
                <div class="btn-group">
                  <button @click="addDiscount" v-if="index === discounts.length - 1" class="btn-add btn-add-green"><i class="fas fa-plus"></i> Desconto</button>
                  <button @click="removeAdditionalDiscount(index)" :disabled="index === 0 && discounts.length === 1" class="btn-remove btn-remove-red"><i class="fas fa-minus"></i> Desconto</button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- Botão para dividir os gastos entre os participantes -->
    <button @click="saveAndDivide" class="btn-primary btn-divide-blue">Dividir os gastos entre os participantes</button>
  </div>
</template>

<script>
export default {
  data() {
    return {
      participants: [],
      increases: [{ name: '', value: '', isPercentage: false }],
      discounts: [{ name: '', value: '', isPercentage: false }]
    };
  },
  created() {
    this.addParticipant(); // Adiciona o primeiro participante e segundo
    this.addParticipant(); 
  },
  methods: {
    addParticipant() {
      this.participants.push({ name: '', items: [{ name: '', value: '' }] });
    },
    addParticipantItem(participantIndex) {
      this.participants[participantIndex].items.push({ name: '', value: '' });
    },
    removeParticipant(participantIndex) {
      this.participants.splice(participantIndex, 1);
    },
    removeParticipantItem(participantIndex, itemIndex) {
      this.participants[participantIndex].items.splice(itemIndex, 1);
    },
    addExpense() {
      this.increases.push({ name: '', value: '', isPercentage: false });
    },
    removeAdditionalExpense(index) {
      if (index > 0 || this.increases.length > 1) {
        this.increases.splice(index, 1);
      }
    },
    addDiscount() {
      this.discounts.push({ name: '', value: '', isPercentage: false });
    },
    removeAdditionalDiscount(index) {
      if (index > 0 || this.discounts.length > 1) {
        this.discounts.splice(index, 1);
      }
    },
    async saveAndDivide(e) {
      e.preventDefault();

      if (this.participants.length < 2) {
        alert('Por favor, adicione pelo menos dois participantes para realizar a divisão dos valores.');
        return;
      }

      console.log('Dados dos participantes:', this.participants);
      console.log('Gastos Adicionais:', this.increases);
      console.log('Descontos Adicionais:', this.discounts);

      const data = {
        participants: this.participants,
        increases: this.increases,
        discounts: this.discounts
      };

      const dataJson = JSON.stringify(data);

      console.log(dataJson);

      const req = await fetch('http://localhost:8080/api/v1/order/splitOrder', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: dataJson
      })

      const res = await req.json();
      console.log(res);
    },
    validateValue(participantIndex, itemIndex) {
      // Converte o valor para número e verifica se é positivo
      let value = parseFloat(this.participants[participantIndex].items[itemIndex].value);
      if (isNaN(value) || value < 0) {
        // Se o valor não for um número positivo, define como vazio
        this.participants[participantIndex].items[itemIndex].value = '';
      } else {
        // Se for um número positivo, atualiza o valor
        this.participants[participantIndex].items[itemIndex].value = value.toString();
      }
    }
  }
};
</script>

<style scoped>
.container {
  max-width: 800px; /* Ajuste a largura conforme necessário */
  margin: 0 auto;
  padding: 20px;
}

.title {
  font-size: 18px;
  font-weight: bold;
}

.section-box {
  border: 1px solid #ddd;
  padding: 15px;
  border-radius: 5px;
  margin-bottom: 15px;
}

.two-column-grid {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 20px;
}

.participant-section,
.additional-section {
  margin-bottom: 15px;
}

.participant-title,
.additional-title {
  font-size: 14px;
  font-weight: bold;
  margin-bottom: 5px;
}

.label {
  font-weight: bold;
}

.input {
  width: calc(100% - 30px);
  padding: 6px;
  margin-bottom: 6px;
  border: 1px solid #ddd;
  border-radius: 3px;
  font-size: 12px;
}

.input-small {
  width: calc(50% - 10px);
}

.item-group {
  margin-bottom: 6px;
}

.btn-group {
  display: flex;
  justify-content: space-between;
  padding: 15px;
}

.btn-add,
.btn-remove {
  background-color:#2ecc71; 
  color: #fff;
  padding: 6px;
  cursor: pointer;
  border-radius: 3px;
  width: calc(30% - 4px);
  font-size: 12px;
  text-align: center;
}

.btn-add:hover,
.btn-remove:hover {
  background-color: #27ae60; 
}

.btn-primary {
  background-color: #0073b1; 
  color: #fff;
  padding: 10px;
  cursor: pointer;
  border-radius: 3px;
  display: block;
  margin-top: 15px;
  width: 100%;
  font-size: 14px;
  text-align: center;
}

.btn-primary:hover {
  background-color: #005684; 
}

.btn-remove-red {
  background-color: #e74c3c; 
  border-radius: 3px;
}

.btn-remove-red:hover {
  background-color: #c0392b; 
}

.btn-divide-blue {
  background-color: #0073b1; 
}

.btn-divide-blue:hover {
  background-color: #005684; 
}

/* Estilo para as caixas */
.pessoas-itens-box,
.additional-expenses-box,
.additional-discounts-box {
  border: 1px solid #ddd;
  border-radius: 5px;
  padding: 8px;
  margin-top: 8px;
}
</style>