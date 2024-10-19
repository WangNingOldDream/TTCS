<template>
    <div ref="compTreeWrapper" style="position: relative;">
        <canvas ref="lineCanvas" :width="canvasW" :height="canvasH" style="position: absolute;left: 0;top: 0;">
        </canvas>
        <div v-for="(item, index) in treeData" v-bind:key="item.order"
            :style="{ position: 'absolute', left: nodePositions[index].x + 'px', top: nodePositions[index].y + 'px', width: nodeW + 'px', height: nodeH + 'px' }"
            style="text-align: center;">
            <img v-if="item.avatar" :src="item.avatar" width="60" height="60" />
            <img v-else :src="logo" width="60" height="60" />
            <ElText style="width: 100px;height: 20px;text-align: center;" truncated>{{ item.name ? item.name : "未知" }}
            </ElText>
            <ElButton v-if="shouldBtnShow(index, role)" type="primary" style="width: 90px;height: 30px;margin-top: 15px" v-on:click="$emit('btnClick', item.order)">{{
                getBtnText(index, role) }}
            </ElButton>
        </div>
    </div>
</template>

<script setup lang="ts">
import { onMounted, ref } from "vue";
import logo from "/logo.png";

const { treeData, againstData, userId, role } = defineProps<{
    treeData: {
        order: number,
        avatar?: string,
        name?: string,
    }[],
    againstData: {
        state: string,
        refereeId: number,
        record?: string
    }[],
    userId: number,
    role: string
}>();

const nodeW = 100;
const nodeH = 140;

const depth = Math.ceil(Math.log2(treeData.length + 1));
const canvasH = nodeH * depth;
const lastLevelNum = treeData.length - (Math.pow(2, depth - 1) - 1);
const leafNum = Math.pow(2, depth - 2) + lastLevelNum / 2;
const canvasW = nodeW * leafNum;

let nodePositions: { x: number, y: number }[] = Array(treeData.length);
for (let j = Math.pow(2, depth - 1); j <= treeData.length; j++) {
    let y = nodeH * (depth - 1);
    let x = nodeW * (j - Math.pow(2, depth - 1));
    nodePositions[j - 1] = { x: x, y: y };
}

for (let i = depth - 1; i > 0; i--) {
    for (let j = Math.pow(2, i - 1); j < Math.pow(2, i); j++) {
        let y = 140 * (i - 1);
        let x = undefined;
        if (2 * j < nodePositions.length) {
            x = (nodePositions[2 * j - 1].x + nodePositions[2 * j].x) / 2;
        }
        else {
            x = nodePositions[j - 2].x + nodeW;
        }
        nodePositions[j - 1] = { x: x, y: y };
    }
}


let compTreeWrapper = ref()
let lineCanvas = ref();

onMounted(() => {
    let ctx = lineCanvas.value.getContext("2d");
    nodePositions.forEach((position, index) => {
        if (2 * (index + 1) < nodePositions.length) {
            ctx.beginPath();
            ctx.moveTo(position.x + nodeW / 2, position.y + 80);
            ctx.lineTo(position.x + nodeW / 2, position.y + 100);
            const leftC = nodePositions[2 * (index + 1) - 1];
            const rightC = nodePositions[2 * (index + 1)];
            ctx.moveTo(leftC.x + nodeW / 2, position.y + 100);
            ctx.lineTo(leftC.x + nodeW / 2, position.y + 140);
            ctx.moveTo(rightC.x + nodeW / 2, position.y + 100);
            ctx.lineTo(rightC.x + nodeW / 2, position.y + 140);
            ctx.moveTo(leftC.x + nodeW / 2, position.y + 100);
            ctx.lineTo(rightC.x + nodeW / 2, position.y + 100);
            ctx.stroke();
        }
    });
    compTreeWrapper.value.style.width = canvasW + 'px';
    compTreeWrapper.value.style.height = canvasH + 'px';
}
)

function shouldBtnShow(index: number, role: string) {
    if (2 * (index + 1) < treeData.length) {
        if (role == "赛事管理员" && againstData[index].state == "已结束") return true;
        if (role == "观众" && againstData[index].record) return true;
        if (role == "裁判员" && againstData[index].refereeId == userId
            && againstData[index].state == "进行中") return true;
    }
    return false;
}

function getBtnText(index: number, role: string) {
    if (role == "赛事管理员") return againstData[index].record ? "重新上传" : "上传";
    if(role=="观众")return "观看";
    if(role == "裁判员")return "记录";
}


</script>

<style scoped></style>