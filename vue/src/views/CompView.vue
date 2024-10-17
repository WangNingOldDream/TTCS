<template>
  <canvas ref="treeCanvas" :width="canvasWidth" :height="canvasHeight">
  </canvas>
</template>

<script setup lang="ts">
import { nextTick, onMounted, ref } from "vue";

let treeData = ref([
  {
    nodeOrder: 1,
    playerId: 1,
  },
]);

for (let i = 2; i < 10; i++) {
  treeData.value.push({
    nodeOrder: i,
    playerId: i
  });
}

const normalNodeW = 100
const excessNodeW = 200
const nodeHeight = 140

const levelNum = Math.ceil(Math.log2(treeData.value.length))+1;
const canvasHeight = nodeHeight * levelNum;
const excessNodeNum = Math.pow(2, levelNum - 1) > treeData.value.length ? treeData.value.length - Math.pow(2, levelNum - 2) : 0;
const canvasWidth = normalNodeW * Math.pow(2, levelNum - 1) + excessNodeW * excessNodeNum;
const nodeNum = excessNodeNum > 0 ? Math.pow(2, levelNum - 1) - 1 + 2 * excessNodeNum : Math.pow(2, levelNum) - 1;

const treeNodes: any[] = [];
for (let j = Math.pow(2, levelNum - 1); j <= nodeNum; j++) {
  let y = nodeHeight * (levelNum - 1);
  let nodeW = excessNodeNum > 0 ? excessNodeW : normalNodeW;
  let x = nodeW * (j - Math.pow(2, levelNum - 1)) + nodeW / 2;
  treeNodes.push({
    nodeOrder: j,
    x: x,
    y: y
  });
}

for (let i = levelNum - 1; i > 0; i--) {
  for (let j = Math.pow(2, i - 1); j < Math.pow(2, i) && j <= nodeNum; j++) {
    let y = 140 * (i - 1);
    let x = undefined;
    let children = treeNodes.filter(element => element.nodeOrder == j * 2 || element.nodeOrder == (j * 2 + 1));
    if (children.length>0) {
      x = (children[0].x + children[1].x) / 2;
    }
    else {
      x = treeNodes.find(element => element.nodeOrder == j - 1).x + normalNodeW;
    }
    treeNodes.push({
      nodeOrder: j,
      x: x,
      y: y
    });
  }
}

let treeCanvas = ref();
onMounted(()=>{
  let ctx = treeCanvas.value.getContext("2d");
ctx.fillStyle= "red";
treeNodes.forEach(node=>{
  ctx.fillRect(node.x-30,node.y+60,60,60);
})
}
)
</script>

<style></style>
