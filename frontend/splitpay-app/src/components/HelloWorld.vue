<template>
  <div>
    <h2>Formulário de Compras</h2>

    <!-- Formulário para cada participante -->
    <div v-for="(participant, participantIndex) in participants" :key="participantIndex">
      <h3>Participante {{ participantIndex + 1 }}</h3>
      <div>
        <label>Nome:</label>
        <input type="text" v-model="participant.name" />
      </div>
      <div v-for="(item, itemIndex) in participant.items" :key="itemIndex">
        <input type="text" v-model="item.name" :placeholder="'Nome do item ' + (itemIndex + 1)" />
        <input type="number" v-model="item.value" :placeholder="'Valor ' + (itemIndex + 1)" />
      </div>
      <button @click="addParticipantItem(participantIndex)">Adicionar Item</button>
      <button v-if="participantIndex > 0" @click="removeParticipant(participantIndex)">Remover Participante</button>
    </div>

    <!-- Campo para gastos adicionais -->
    <div>
      <h3>Gastos Adicionais</h3>
      <div v-for="(expense, index) in additionalExpenses" :key="index">
        <input type="text" v-model="expense.name" :placeholder="'Nome do gasto ' + (index + 1)" />
        <input type="text" v-model="expense.value" :placeholder="'Valor ' + (index + 1)" />
        <select v-model="expense.isPercentage">
          <option value="true">Porcentagem</option>
          <option value="false">Valor Fixo</option>
        </select>
      </div>
      <button @click="addExpense">Adicionar Gasto</button>
    </div>

    <!-- Campo para descontos adicionais -->
    <div>
      <h3>Descontos Adicionais</h3>
      <div v-for="(discount, index) in additionalDiscounts" :key="index">
        <input type="text" v-model="discount.name" :placeholder="'Nome do desconto ' + (index + 1)" />
        <input type="text" v-model="discount.value" :placeholder="'Valor ' + (index + 1)" />
        <select v-model="discount.isPercentage">
          <option value="true">Porcentagem</option>
          <option value="false">Valor Fixo</option>
        </select>
      </div>
      <button @click="addDiscount">Adicionar Desconto</button>
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
    addExpense() {
      this.additionalExpenses.push({ name: '', value: '', isPercentage: false });
    },
    addDiscount() {
      this.additionalDiscounts.push({ name: '', value: '', isPercentage: false });
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