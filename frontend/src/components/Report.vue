<template>
    <div>
        <b-modal id="modalReport" :hide-footer="true" title="REPORT CONTENT">
            <b-form-group label="Report for:" v-slot="{ ariaDescribedby }" style="margin-left: 120px;">
            <b-form-radio-group
                id="btn-radios-3"
                v-model="selected"
                :options="options"
                :aria-describedby="ariaDescribedby"
                button-variant="outline-danger"
                name="radio-btn-stacked"
                buttons
                stacked
            ></b-form-radio-group>
            </b-form-group>
            <b-button variant="danger" style="margin-left: 200px; margine-top: 10px; margine-bottom: 10px;" v-on:click="addReport">Report</b-button>
        </b-modal>
    </div>
</template>

<script>

import axios from 'axios'

export default {
    name: 'Report',
    computed: {
        User() {
            return this.$store.getters.getUser
        },
        entity() {
            return this.$store.getters.getEntity
        }
    },
    data() {
        return {
            selected: null,
            options: []
        }
    },
    methods: {
        addReport() {
            if(this.selected == null) {
                alert("Please select reason for reporting.")
                return
            }
            var add = {
                userId: this.User.id,
                postId: this.entity.id,
                reason: this.selected
            }
            axios.post(this.$store.getters.getAdminAPI + "/api/report/add-report", add)
                .then(r => {
                    if(r.data == 'ok') {
                        alert("Content is successfully reported!")
                    }
                    else {
                        alert("Content is already reported by you!")
                    }
                })
        }
    },
    mounted() {
        axios.get(this.$store.getters.getAdminAPI + "/api/reporttype/get-report-types")
                .then(r => {
                    this.options = JSON.parse(JSON.stringify(r.data))
                })
        // this.$root.$on('bv::modal::show', (bvEvent, modalReport) => {
        //     axios.get("http://localhost:8084/api/reporttype/get-report-types")
        //         .then(r => {
        //             this.options = JSON.parse(JSON.stringify(r.data))
        //         })
        // })
    }
}
</script>

<style scoped>

</style>