<template>
  <div>
    <h2>Formulário de Compras</h2>

    <!-- Formulário para a pessoa -->
    <div>
      <h3>Seus Itens</h3>
      <div v-for="(item, index) in items" :key="index">
        <input type="text" v-model="item.name" placeholder="Nome do item" />
        <input type="number" v-model="item.value" placeholder="Valor" />
      </div>
      <button @click="addItem">Adicionar Item</button>
    </div>

    <!-- Formulário para amigos -->
    <div>
      <h3>Amigos</h3>
      <div v-for="(friend, friendIndex) in friends" :key="friendIndex">
        <h4>Amigo {{ friendIndex + 1 }}</h4>
        <input type="text" v-model="friend.name" placeholder="Nome do amigo" />
        <div v-for="(item, itemIndex) in friend.items" :key="itemIndex">
          <input type="text" v-model="item.name" :placeholder="'Nome do item ' + (itemIndex + 1)" />
          <input type="number" v-model="item.value" :placeholder="'Valor ' + (itemIndex + 1)" />
        </div>
        <button @click="addFriendItem(friendIndex)">Adicionar Item</button>
      </div>
      <button @click="addFriend">Adicionar Amigo</button>
    </div>

    <!-- Botão para salvar e dividir -->
    <button @click="saveAndDivide">Salvar e Dividir</button>
  </div>
</template>

<script>
export default {
  data() {
    return {
      items: [{ name: '', value: '' }], // Itens da pessoa
      friends: [{ name: '', items: [{ name: '', value: '' }] }] // Amigos e seus itens
    };
  },
  methods: {
    addItem() {
      this.items.push({ name: '', value: '' });
    },
    addFriendItem(friendIndex) {
      this.friends[friendIndex].items.push({ name: '', value: '' });
    },
    addFriend() {
      this.friends.push({ name: '', items: [{ name: '', value: '' }] });
    },
    saveAndDivide() {
      // Aqui você pode salvar os dados ou realizar a lógica de divisão dos valores
      console.log('Itens da pessoa:', this.items);
      console.log('Amigos:', this.friends);
      // Lógica de divisão dos valores...
    }
  }
};
</script>

<style>
/* Adicione estilos conforme necessário */
</style>