<template>
    <div>
        <div v-for="item in list['element']" :key=item>
            <v-card
            class="mx-auto"
            max-width="344"
            outlined="">
                <v-card-text>
                    <div><h3>{{item}}</h3></div>
                    <v-spacer></v-spacer>
                </v-card-text>
        </v-card>

        </div>
        
    </div>
    
</template>

<script>
export default {
    name: 'Cards',
    props:["list"]
    
}
</script>

<style scoped>

</style>