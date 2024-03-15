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
      participants: [{ name: '', items: [{ name: '', value: '' }] }] 
    };
  },
  methods: {
    addParticipant() {
      this.participants.push({ name: '', items: [{ name: '', value: '' }] });
    },
    addParticipantItem(participantIndex) {
      this.participants[participantIndex].items.push({ name: '', value: '' });
    },
    saveAndDivide() {
      console.log('Dados dos participantes:', this.participants);
    }
  }
};
</script>

<style>
</style>