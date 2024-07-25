/**
 * @author:     zeddic
 * @description:  接口开发
 * @date:    2024/7/24 下午2:33
 */
import axios from 'axios';

const apiHostURL = process.env.API_HOST_URL ? process.env.API_HOST_URL : '';

export const strategyArmory = async (strategyId: number) => {
    try {
        const response = await axios.get(`${apiHostURL}/api/v1/raffle/strategy_armory?strategyId=${strategyId}`,{
            headers:{
                'Content-Type': 'application/json'
            }
        });
        return response.data;
    } catch (error) {
        return {
            code: "500",
            info: "调用失败",
            data: {}
        };
    }
}

export const queryRaffleAwardList = async (strategyId: number) => {
    try {
        const response = await axios.post(`${apiHostURL}/api/v1/raffle/query_raffle_award_list`, strategyId,{
            headers:{
                'Content-Type': 'application/json'
            }
        });
        return response.data;
    } catch (error) {
        return {
            code: "500",
            info: "调用失败",
            data: {}
        };
    }
}

export const randomRaffle = async (strategyId: number) => {
    try {
        const response = await axios.post(`${apiHostURL}/api/v1/raffle/random_raffle`, strategyId,{
            headers:{
                'Content-Type': 'application/json'
            }
        });
        return response.data;
    } catch (error) {
        return {
            code: "500",
            info: "调用失败",
            data: {}
        };
    }
}