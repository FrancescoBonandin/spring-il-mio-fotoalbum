<script setup>
 import { defineProps, defineEmits} from 'vue';  

 import axios from 'axios';

    
    //EMIT
    const emits = defineEmits(["backToIndex", "edit", "deletePhoto"])
  


    // PROPS
    const props = defineProps({
        selectedPhoto: {
            type: Object,
            required: false
        }
    });


</script>

<template>
    
    <section id="show" class="row">
        
        <div class="col">

            <div class="row justify-content-center ">
    
                <div class="col-6 g-4" >
    
                    <div class="card"  >
    
                        <img class="img-fluid card-img-top p-3" :src="props.selectedPhoto.url" alt="">
                        
                        <div class="card-body">

                            <div class="col">
                                {{ props.selectedPhoto.title }}
                            </div>
    
                            <div class="col">
                                {{ props.selectedPhoto.description }}
                            </div>
                            
                            <div v-for="category in props.selectedPhoto.categories" :key="category.id" class="col">
                                {{ category.name }}
                            </div>

                            <div>
                                {{ props.selectedPhoto.user.username}}
                            </div>
                        </div>
                        
                    </div>
    
                </div>
                
            </div>

            <div class="row justify-content-center ">

                <div class="col-auto g-3 text-center">
                  
                    <button class="btn btn-primary" @click="$emit('backToIndex')" type="button"> back </button>
                    
                </div>

            </div>

        </div>

    </section>
</template>

<style lang="scss" scoped>
</style>