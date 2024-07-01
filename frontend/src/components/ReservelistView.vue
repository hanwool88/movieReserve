<template>

    <v-data-table
        :headers="headers"
        :items="reservelist"
        :items-per-page="5"
        class="elevation-1"
    ></v-data-table>

</template>

<script>
    const axios = require('axios').default;

    export default {
        name: 'ReservelistView',
        props: {
            value: Object,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            headers: [
                { text: "id", value: "id" },
                { text: "reserveId", value: "reserveId" },
                { text: "movieId", value: "movieId" },
                { text: "reserveStatus", value: "reserveStatus" },
            ],
            reservelist : [],
        }),
          async created() {
            var temp = await axios.get(axios.fixUrl('/reservelists'))

            temp.data._embedded.reservelists.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])

            this.reservelist = temp.data._embedded.reservelists;
        },
        methods: {
        }
    }
</script>

