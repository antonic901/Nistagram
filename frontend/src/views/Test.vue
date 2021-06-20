<template>
    <div class="container-2">
        <div v-on:click="change"> 
            <b-img-lazy class="item-5" :src="'https://nistagramstorage.s3.eu-central-1.amazonaws.com/antonic901-post-1624186906718-image-1.jpg'"></b-img-lazy>
        </div>
        <div v-on:click="change"> 
            <b-img-lazy class="item-5" :src="'https://nistagramstorage.s3.eu-central-1.amazonaws.com/antonic901-post-1624186906718-image-1.jpg'"></b-img-lazy>
        </div>
        
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
export default {
    name: 'Test',
    methods: {
        change() {
            alert("hello")
        }
    }
}
</script>

<style>
.container-1 {
    display: flex;
    flex-direction: column;
}

.container-2 {
    display: flex;
    flex-direction: row;
}

.container-3 {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
}

.item-1 {
    flex-grow: 1;
}

.item-2 {
    flex-grow: 1;
}

.itme-3 {
    align-self: center;
}

.item-4 {
    align-self: center;
}

.item-5 {
    width: 292px;
    height: 292px;
    margin: 10px;
}

.button {
    margin:10px;
}
</style>