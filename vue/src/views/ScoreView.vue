<template>
    <div
        style="height: 100%;display: flex;flex-direction: column;padding: 10px;box-sizing: border-box;background-color: #ff4d00;">
        <ElRow>
            <ElCol :span="8" style="text-align: left;">
                <img :src="players[0].avatar" style="width: 60px;height: 60px;" />
                <h5 style="margin: 0;">{{ players[0].name }}</h5>
            </ElCol>
            <ElCol :span="8">
                <div style="text-align: center;">
                    <h2>{{ players[0].matchScore + ":" + players[1].matchScore }}</h2>
                    <ElPopover placement="bottom" trigger="click">
                        <template #reference>
                            <ElButton>历史比分</ElButton>
                        </template>
                        <h3 v-for="(item, index) in players[0].gameScores" v-bind:key="index"
                            style="text-align: center;">
                            {{ players[0].gameScores[index] + ":" + players[1].gameScores[index] }}
                        </h3>
                    </ElPopover>
                </div>
            </ElCol>
            <ElCol :span="8" style="text-align: right;">
                <img :src="players[1].avatar" style="width: 60px;height: 60px;" />
                <h5 style="margin: 0;">{{ players[1].name }}</h5>
            </ElCol>
        </ElRow>
        <ElRow style="height: auto;justify-content: space-evenly;align-items: center;flex: 1;">
            <div style="display: flex;justify-content:center;align-items: center;width: 30%;height: 100%;">
                <ElButton style="width: 300px;height: 400px;opacity: 0.9;font-size: 200px;" v-on:click="addScore(0)" :style="players[0].serving?servingBtn:''">
                    {{ players[0].gameScores.slice(-1)[0] }}</ElButton>
            </div>
            <ElDivider direction="vertical" style="height: 100%;border-width: 2px;"></ElDivider>
            <div style="display: flex;justify-content:center;align-items: center;width: 30%;height: 100%;">
                <ElButton style="width: 300px;height: 400px;opacity: 0.9;font-size: 200px;" v-on:click="addScore(1)"  :style="players[1].serving?servingBtn:''">{{
                    players[1].gameScores.slice(-1)[0] }}</ElButton>
            </div>
        </ElRow>
    </div>
    <ElDialog>

    </ElDialog>
</template>

<script setup lang="ts">

import { ref } from "vue"
import logo from "/logo.png"
import ball from "@/assets/ball.jpg"

const servingBtn = {
    "background": "url("+ball+")",
    "background-size": "100% 100%"
}

let players = ref([{
    avatar: logo,
    name: "123",
    gameScores: [11, 0],
    matchScore: 1,
    serving:true
},
{
    avatar: logo,
    name: "456",
    gameScores: [7, 0],
    matchScore: 0,
    serving:false
}
]);

function addScore(pos: number) {
    //get:againstId+节点order=>小局是否结束+大局是否结束
}

function update() {
    //get:againstId=>大局比分和小局比分
}

</script>

<style scoped>

</style>