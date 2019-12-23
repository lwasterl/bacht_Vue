<template>
    <v-card
        class="mx-auto"
        max-width="500"
    >
        <v-card-text>
        <div>{{value}}</div>
        <div> {{nb}}</div>
        </v-card-text>
    </v-card>
</template>

<script>
export default {
    name:'Cards',
    props:["value", "nb"]
}
</script>

<style scoped>

</style>