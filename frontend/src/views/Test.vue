<template>
    <div class="container-1">
        <div class="card container-1">
            <b-img class="item-4" :src="require('../static/picture.jpg')" style="width:250px; height:250px; margin-bottom:10px;"></b-img>
            <div class="container-2">
                <label class="item-3">Nikola Antonic</label>
                <label class="item-3">Propalica</label>
                <label class="item-3">pcserviskac.com</label>
                <button class="btn btn-primary" style="margin:5px; margin-right:20px; border-radius:20px;">Follow</button>
                <button class="btn btn-outline-primary" style="margin:5px; margin-right:20px; border-radius:20px;" v-on:click="clickEdit">Edit</button>
            </div>
        </div>
        <div v-if="showEdit">
            <EditProfile/>
        </div>
        
        <!-- <b-card
            title="Card Title"
            img-src="https://picsum.photos/600/300/?image=25"
            img-alt="Image"
            img-top
            tag="article"
            style="max-width: 20rem;"
            class="mb-2">
            <b-card-text>
                Some quick example text to build on the card title and make up the bulk of the card's content.
            </b-card-text>

            <b-button>Follow</b-button>
            <b-button>Edit</b-button>
        </b-card> -->

        <!-- <div class="container-2">
            <b-button class="item-1 button">Dugme3</b-button>
        </div>
        <div class="container-2">
            <b-button class="item-2 button">Dugme1</b-button>
            <b-button class="item-1 button">Dugme2</b-button>
        </div>
        <div class="container-3">
            <b-button class="button item-4">Dugme4</b-button>
            <div class="container-1"> 
                <b-button class="button">Dugme5</b-button>
                <b-button class="button">Dugme5</b-button>
                <b-button class="button">Dugme5</b-button>
                <b-button class="button">Dugme5</b-button>
                <b-button class="button">Dugme5</b-button>
                <b-button class="button">Dugme5</b-button>
                <b-button class="button">Dugme5</b-button>
            </div>
            <b-button class="button item-4">Dugme6</b-button>
        </div> -->
    </div>
</template>

<script>

import EditProfile from '../components/EditProfile.vue'

export default {
    name: 'Test',
    components: {
        EditProfile
    },
    data() {
        return {
            showEdit: false
        }
    },
    methods: {
        clickEdit() {
            if(this.showEdit) this.showEdit = false
            else this.showEdit = true
        }
    }
}
</script>

<style scoped>
.container-1 {
    display: flex;
    flex-direction: row;
}

.container-2 {
    display: flex;
    flex-direction: column;
    justify-content: center;
    margin-left: 20px;
}

.container-3 {
    display: flex;
    flex-direction: column;
    margin: 5px;
    justify-content: space-between;
}

.container-4 {
    display: flex;
    flex-direction: column;
    justify-content: center;
}

.card {
    background: white;
    margin: 5px;
}

.item-1 {
    flex-grow: 1;
}

.item-2 {
    flex-grow: 1;
}

.item-3 {
    align-self: center;
    margin-right: 20px;
}

.item-4 {
    align-self:center;
}

.button {
    margin:10px;
}
</style>