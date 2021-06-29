<template>
    <div id="main">
        <div id="container">
            <b-form-input type="text" @keyup="search" id="search-input" style="margin:5px;border-radius:20px;" placeholder="search locations" v-model="query"></b-form-input>
            <div v-if="results.length > 0" id="results">
                <b-list-group style="max-width:300px;">
                    <b-list-group-item v-for="r in results" :key="r.place_id" class="d-flex align-items-center">
                        <p @click="select(r)" ><b>{{r.display_name}}</b></p>
                    </b-list-group-item>
                </b-list-group>
            </div>
        </div>
    </div>
</template>

<script>

import axios from 'axios'

export default {
    name: 'Map',
    data: () => ({
        timeout: null,
        results: [],
        selected: null,
        query: '',
    }),

    methods: {
        select: function(r) {
            this.selected = r;
            this.results = [];
            this.$emit('selected', r);
            console.log(this.selected)
        },
        search: function() {
            clearTimeout(this.timeout);
            this.results = [];
            let self = this;
            this.timeout = setTimeout(function() {
                axios.get('https://us1.locationiq.com/v1/search.php?key=8d176a2a842649&format=json&addressdetails=1&limit=5&q=' + self.query)
                    .then(r => {
                        self.results = r.data;
                    })
            }, 1000);
        },
    },
}
</script>

<style scoped>
    #container {
        position: relative;
        display: inline-block;
    }

    #results {
        position: absolute;
        top: 100%;
        left: 0;
        right: 0;
        z-index: 99;
        border: solid 1px #eee;
    }

    #container input {
        margin-bottom: 0;
    }

    .result {
        cursor: pointer;
        border: solid 1px #eee;
    }

    .result:hover {
        background: #eee;
    }
</style>