/**
 * @author:     zeddic
 * @description:  策略装配按钮组件
 * @date:    2024/7/25 下午2:58
 */
"use client"
import {strategyArmory} from "@/apis/API";

export const StrategyArmory = () =>{
    const strategyArmoryHandler = async () => {
        const queryParams = new URLSearchParams(window.location.search);
        const strategyId = Number(queryParams.get('strategyId'));
        if (!strategyId){
            window.alert("请在请求地址中，配置 strategyId 值，如：http://localhost:3000/?strategyId=100006")
            return;
        }
        const result = await strategyArmory(strategyId);
        const {code , info} = result;
        if (code != "0000"){
            window.alert("策略装配失败 code:" + code + " info:" + info)
            return
        }else {
            window.alert("策略装配成功！ code:" + code + " info:" + info)
        }
    }

    return <div
        className="px-6 py-2 mb-8 text-white bg-blue-500 rounded-full shadow-lg hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-blue-300"
        style={{cursor: "pointer"}}
        onClick={strategyArmoryHandler}
    >
        装配抽奖
    </div>



}