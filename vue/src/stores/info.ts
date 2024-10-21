import { ref } from 'vue'
import { defineStore } from 'pinia'

export const useInfoStore = defineStore(
    'info',
    () => {
        const userId = ref(0);
        const compId = ref(0);
        const role = ref("");

        return { userId, compId, role };
    },
    {
        persist: true,
    },
)