<template>
  <div class="container">
    <h2>Gastos para dividir entre os Participantes</h2>

    <!-- Botão para adicionar participante -->
    <button @click="addParticipant" class="btn btn-success">Adicionar Participante</button>

    <!-- Formulário para cada participante -->
    <div v-for="(participant, participantIndex) in participants" :key="participantIndex" class="participant-section">
      <div class="participant-info">
        <h3>Participante {{ participantIndex + 1 }}</h3>
        <div class="form-group">
          <label for="participantName{{ participantIndex }}">Nome:</label>
          <input type="text" v-model="participant.name" id="participantName{{ participantIndex }}" class="form-control" />
        </div>
      </div>
      <div class="participant-items">
        <div v-for="(item, itemIndex) in participant.items" :key="itemIndex">
          <input type="text" v-model="item.name" :placeholder="'Nome do item ' + (itemIndex + 1)" class="form-control" />
          <input type="number" v-model="item.value" :placeholder="'Valor ' + (itemIndex + 1)" class="form-control" />
          <button @click="removeParticipantItem(participantIndex, itemIndex)" :disabled="itemIndex === 0 && participant.items.length === 1" class="btn btn-danger">Remover</button>
        </div>
        <button @click="addParticipantItem(participantIndex)" class="btn btn-primary">Adicionar Item</button>
      </div>
      <button v-if="participantIndex > 0" @click="removeParticipant(participantIndex)" class="btn btn-danger">Remover Participante</button>
    </div>

    <!-- Campo para gastos adicionais e descontos adicionais -->
    <div class="additional-section">
      <!-- Campo para gastos adicionais -->
      <div class="additional-expenses">
        <h3>Gastos Adicionais</h3>
        <div v-for="(expense, index) in additionalExpenses" :key="index" class="form-group">
          <input type="text" v-model="expense.name" :placeholder="'Nome do gasto ' + (index + 1)" class="form-control" />
          <input type="text" v-model="expense.value" :placeholder="'Valor ' + (index + 1)" class="form-control" />
          <select v-model="expense.isPercentage" class="form-control">
            <option value="true">Porcentagem</option>
            <option value="false">Valor Fixo</option>
          </select>
          <button @click="removeAdditionalExpense(index)" :disabled="index === 0 && additionalExpenses.length === 1" class="btn btn-danger">Remover</button>
        </div>
        <button @click="addExpense" class="btn btn-primary">Adicionar Gasto</button>
      </div>

      <!-- Campo para descontos adicionais -->
      <div class="additional-discounts">
        <h3>Descontos Adicionais</h3>
        <div v-for="(discount, index) in additionalDiscounts" :key="index" class="form-group">
          <input type="text" v-model="discount.name" :placeholder="'Nome do desconto ' + (index + 1)" class="form-control" />
          <input type="text" v-model="discount.value" :placeholder="'Valor ' + (index + 1)" class="form-control" />
          <select v-model="discount.isPercentage" class="form-control">
            <option value="true">Porcentagem</option>
            <option value="false">Valor Fixo</option>
          </select>
          <button @click="removeAdditionalDiscount(index)" :disabled="index === 0 && additionalDiscounts.length === 1" class="btn btn-danger">Remover</button>
        </div>
        <button @click="addDiscount" class="btn btn-primary">Adicionar Desconto</button>
      </div>
    </div>

    <!-- Botão para dividir os gastos entre os participantes -->
    <button @click="saveAndDivide" class="btn btn-primary">Dividir os gastos entre os participantes</button>
  </div>
</template>

<script>
export default {
  data() {
    return {
      participants: [{ name: '', items: [{ name: '', value: '' }] }],
      additionalExpenses: [{ name: '', value: '', isPercentage: false }],
      additionalDiscounts: [{ name: '', value: '', isPercentage: false }]
    };
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
      this.additionalExpenses.push({ name: '', value: '', isPercentage: false });
    },
    removeAdditionalExpense(index) {
      if (index > 0 || this.additionalExpenses.length > 1) {
        this.additionalExpenses.splice(index, 1);
      }
    },
    addDiscount() {
      this.additionalDiscounts.push({ name: '', value: '', isPercentage: false });
    },
    removeAdditionalDiscount(index) {
      if (index > 0 || this.additionalDiscounts.length > 1) {
        this.additionalDiscounts.splice(index, 1);
      }
    },
    saveAndDivide() {
      console.log('Dados dos participantes:', this.participants);
      console.log('Gastos Adicionais:', this.additionalExpenses);
      console.log('Descontos Adicionais:', this.additionalDiscounts);
    }
  }
};
</script>

<style scoped>
.container {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
}

.participant-section {
  margin-bottom: 20px;
  border: 1px solid #ccc;
  padding: 10px;
}

.participant-info {
  margin-bottom: 10px;
}

.form-group {
  margin-bottom: 10px;
}

.additional-section {
  margin-top: 20px;
}

.btn {
  margin-right: 10px;
}
</style>