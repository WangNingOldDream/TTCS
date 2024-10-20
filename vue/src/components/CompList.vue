<template>
    <ul class="noneStyleUl" ref="scroll" v-infinite-scroll="load" :infinite-scroll-disabled="scrollDisabled"
            infinite-scroll-distance="10" style="height:580px;margin-bottom: 0;overflow: auto;">
            <li v-for="item in comps" v-bind:key="item.name">
               <ElCard v-on:click="$emit('cardClick', item.id, item.name)" shadow="hover">
                  <img :src="item.cover" style="width: 100%;height: 100%;" />
                  <template #footer>
                     <ElRow justify="center">
                        <ElText style="width: 200px;" truncated size="large">
                           {{ item.name }}
                        </ElText>
                        <ElTag :type="elTagType[item.state as keyof typeof elTagType]">
                           {{ item.state }}</ElTag>
                     </ElRow>
                  </template>
               </ElCard>
            </li>
         </ul>
</template>

<script setup lang="ts">
import type { EpPropMergeType } from "element-plus/es/utils/vue/props/types";

type TagType = EpPropMergeType<StringConstructor, "primary" | "success" | "danger" | "warning" | "info", unknown>;

const { comps, scrollDisabled } = defineProps<{
    comps: {
      id:number,
        cover: string,
        name: string,
        state: string
    }[],
    scrollDisabled: boolean
}>();

const emit = defineEmits<{
  load: [],
  cardClick: [id:number,name: string]
}>()

const elTagType = {
   "准备中": "primary" as TagType,
   "进行中": "success" as TagType,
   "已结束": "danger" as TagType,
};

function load(){
    emit('load');
}

</script>

<style scoped>
.noneStyleUl {
   display: flex;
   flex-wrap: wrap;
   justify-content: space-evenly;
   list-style: none;
   padding: 0;
}
.el-card {
   width: 360px;
   height: 280px;
   margin-bottom: 10px;
}

:deep(.el-card__body) {
   width: 320px;
   height: 180px;
}
</style>