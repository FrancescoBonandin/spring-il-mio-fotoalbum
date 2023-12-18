<script setup>

import {defineProps, defineEmits, ref } from 'vue';
import axios from 'axios';

// PROPS

// DATA

const message = ref({
    email: "",
    body: ""
});

// EMITS
const emits = defineEmits(["back", "created", "edited"]);


// FUNCTIONS
const submitWAxios = async () => {
    
    const data = await axios.post(
        "http://localhost:8080/api/message/create", 
        message.value
    );

    console.log("data", data);

    message.value.email=""
    message.value.body=""

    return;

 }

</script>

<template>
    <section id="form" class="row justify-content-center">
    
          <form @submit.prevent="submitWAxios" class="col-6" method="POST" >
            <h2 class="text-center my-5">

                <span>
                    Send a New message
                </span>

            </h2>

            <div>
        
                <label class="form-label" for="email">
                    Email
                </label>
                    
                <input class="mb-2 form-control" type="email" id="email" name="email" placeholder="email" v-model="message.email" >
                
            </div>
            
            <div>

                <label class="form-label" for="body">
                    Corpo
                </label>
                
                <textarea class="mb-2 form-control" id="body" name="body" placeholder="body" v-model="message.body"></textarea>
                
            </div>
            
            <div class=" mb-4 row justify-content-evenly">

                <div class="col-auto">

                    <button class="btn btn-primary" type="submit">
                      Send
                    </button>

                </div>

                <div class="col-auto">

                    <input class="btn btn-primary" type="reset" >

                </div>
                
            </div>
        
        </form>

    </section>
</template>

<style lang="scss" scoped>
</style>