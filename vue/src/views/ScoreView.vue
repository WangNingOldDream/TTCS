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
                    <el-popover placement="bottom" trigger="click">
                        <template #reference>
                            <el-button>历史比分</el-button>
                        </template>
                        <h3 v-for="(item,index) in players[0].gameScores" v-bind:key="index" style="text-align: center;">
                            {{ players[0].gameScores[index]+":"+ players[1].gameScores[index]}}
                        </h3>
                    </el-popover>
                </div>
            </ElCol>
            <ElCol :span="8" style="text-align: right;">
                <img :src="players[1].avatar" style="width: 60px;height: 60px;" />
                <h5 style="margin: 0;">{{ players[1].name }}</h5>
            </ElCol>
        </ElRow>
        <ElRow style="height: auto;justify-content: space-evenly;align-items: center;flex: 1;">
            <div style="text-align: center;">
                <ElButton v-on:click="addScore(0)">+</ElButton>
                <h1>{{ players[0].gameScores.slice(-1)[0] }}</h1>
            </div>
            <ElDivider direction="vertical" style="height: 100%;border-width: 2px;"></ElDivider>
            <div style="text-align: center;">
                <ElButton v-on:click="addScore(1)">+</ElButton>
                <h1>{{ players[1].gameScores.slice(-1)[0] }}</h1>
            </div>
        </ElRow>
    </div>
</template>

<script setup lang="ts">

import { ref } from "vue"
import logo from "/logo.png"

let players = ref([{
    avatar: logo,
    name: "123",
    gameScores: [11,0],
    matchScore: 1,
},
{
    avatar: logo,
    name: "456",
    gameScores: [7,0],
    matchScore: 0
}
]);

function addScore(pos:number){
    //get:againstId+节点order=>小局是否结束+大局是否结束
}

function update(){
    //get:againstId=>大局比分和小局比分
}

</script>

<style scoped></style>