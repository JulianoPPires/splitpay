<template>
  <div>
    <h2>Gastos para dividir entre os Participantes</h2>

    <!-- Formulário para cada participante -->
    <div v-for="(participant, participantIndex) in participants" :key="participantIndex" class="participant-section">
      <div class="participant-info">
        <h3>Participante {{ participantIndex + 1 }}</h3>
        <div>
          <label>Nome:</label>
          <input type="text" v-model="participant.name" />
        </div>
      </div>
      <div class="participant-items">
        <div v-for="(item, itemIndex) in participant.items" :key="itemIndex">
          <input type="text" v-model="item.name" :placeholder="'Nome do item ' + (itemIndex + 1)" />
          <input type="number" v-model="item.value" :placeholder="'Valor ' + (itemIndex + 1)" />
          <button @click="removeParticipantItem(participantIndex, itemIndex)" :disabled="itemIndex === 0 && participant.items.length === 1">Remover</button>
        </div>
        <button @click="addParticipantItem(participantIndex)">Adicionar Item</button>
      </div>
      <button v-if="participantIndex > 0" @click="removeParticipant(participantIndex)" class="remove-participant-btn">Remover Participante</button>
    </div>

    <!-- Campo para gastos adicionais e descontos adicionais -->
    <div class="additional-section">
      <!-- Campo para gastos adicionais -->
      <div class="additional-expenses">
        <h3>Gastos Adicionais</h3>
        <div v-for="(expense, index) in additionalExpenses" :key="index">
          <input type="text" v-model="expense.name" :placeholder="'Nome do gasto ' + (index + 1)" />
          <input type="text" v-model="expense.value" :placeholder="'Valor ' + (index + 1)" />
          <select v-model="expense.isPercentage">
            <option value="true">Porcentagem</option>
            <option value="false">Valor Fixo</option>
          </select>
          <button @click="removeAdditionalExpense(index)" :disabled="index === 0 && additionalExpenses.length === 1">Remover</button>
        </div>
        <button @click="addExpense">Adicionar Gasto</button>
      </div>

      <!-- Campo para descontos adicionais -->
      <div class="additional-discounts">
        <h3>Descontos Adicionais</h3>
        <div v-for="(discount, index) in additionalDiscounts" :key="index">
          <input type="text" v-model="discount.name" :placeholder="'Nome do desconto ' + (index + 1)" />
          <input type="text" v-model="discount.value" :placeholder="'Valor ' + (index + 1)" />
          <select v-model="discount.isPercentage">
            <option value="true">Porcentagem</option>
            <option value="false">Valor Fixo</option>
          </select>
          <button @click="removeAdditionalDiscount(index)" :disabled="index === 0 && additionalDiscounts.length === 1">Remover</button>
        </div>
        <button @click="addDiscount">Adicionar Desconto</button>
      </div>
    </div>

    <!-- Botão para adicionar participante -->
    <button @click="addParticipant">Adicionar Participante</button>

    <!-- Botão para salvar e dividir -->
    <button @click="saveAndDivide">Salvar e Dividir</button>
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

<style>

</style>