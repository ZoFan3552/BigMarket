/**
 * @author:     zeddic
 * @description:  接口开发
 * @date:    2024/7/24 下午2:33
 */
import axios from 'axios';

const apiHostURL = process.env.API_HOST_URL ? process.env.API_HOST_URL : '';

export const queryRaffleAwardList = async (strategyId: number) => {
    try {
        const response = await axios.get(`${apiHostURL}/api/v1/raffle/query_raffle_award_list?strategyId=${strategyId}&apipost_id=2cfe270eb9d002`);
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
        const response = await axios.get(`${apiHostURL}/api/v1/raffle/random_raffle?strategyId=${strategyId}&apipost_id=2d00e7a039d012`);
        return response.data;
    } catch (error) {
        return {
            code: "500",
            info: "调用失败",
            data: {}
        };
    }
}