<template>
    <v-form>
        <v-container>
            
            <v-row align="center" justify="center">
                <v-col class="d-flex" cols="12" sm="4">
                    
                    <v-select
                    :items="items"
                    filled
                    label="Action Disponible"
                    v-model="item_selected"
                    ></v-select>
                </v-col>

                <v-col cols="12" sm="4">
                    <v-text-field
                    label="Titre du board"
                    v-model="texte"
                    ></v-text-field>
                </v-col>

            </v-row>
                  




        </v-container>
      <Tell v-if="item_selected==='Tell'" :titre="texte" :element="element" :user="user"/>
      <Get v-if="item_selected==='Get'" :titre="texte" :element="element" :user="user"/>
      <Ask v-if="item_selected==='Ask'" :titre="texte" :element="element" :user="user"/>

    </v-form>

    
</template>


<script>
import Tell from './Tell'
import Get from './Get'
import Ask from './Ask'
import boards from './boards.json';
export default {
     name: 'Main',
     props:["user"],
     components:{
         Tell, Get, Ask
     },
     data: () =>({
         texte:"",
         tmp_object:[],
         items: ['Tell', 'Get', 'Ask'],
         item_selected:"Tell",
         dialog_select: false,
         nb_elem:1,
         boards:boards,
         element:""
     }),
     methods:{

         send: function(){

        }
    }
}

</script>

<style scoped>

</style>