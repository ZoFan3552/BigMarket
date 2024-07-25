/**
 * @author:     zeddic
 * @description:  大营销九宫格
 * @date:    2024/7/24 下午1:35
 */
"use client"
import React, {useState, useRef, useEffect} from 'react'
// @ts-ignore
import {LuckyGrid} from '@lucky-canvas/react'
import {queryRaffleAwardList, randomRaffle} from "@/apis/API";
import {RaffleAwardType} from "@/types/RaffleAwardType";


interface Font {
    id?: string;
    text: string;
    top: string;
}

interface Prize {
    x: number;
    y: number;
    fonts: Font[];
}

export const LuckyGridPage = () => {

    // 背景
    const [blocks] = useState([
        {padding: '10px', background: '#869cfa'}
    ])


    const [prizes, setPrizes] = useState<Prize[]>([
        {x: 0, y: 0, fonts: [{id: "0", text: 'A', top: '35%'}]},
        {x: 1, y: 0, fonts: [{id: "0", text: 'B', top: '35%'}]},
        {x: 2, y: 0, fonts: [{id: "0", text: 'C', top: '35%'}]},
        {x: 2, y: 1, fonts: [{id: "0", text: 'D', top: '35%'}]},
        {x: 2, y: 2, fonts: [{id: "0", text: 'E', top: '35%'}]},
        {x: 1, y: 2, fonts: [{id: "0", text: 'F', top: '35%'}]},
        {x: 0, y: 2, fonts: [{id: "0", text: 'G', top: '35%'}]},
        {x: 0, y: 1, fonts: [{id: "0", text: 'H', top: '35%'}]},
    ])

    const queryRaffleAwardListHandler = async () => {
        const queryParams = new URLSearchParams(window.location.search);
        const strategyId = Number(queryParams.get('strategyId'));
        const result = await queryRaffleAwardList(strategyId);
        const {code, info, data} = result;
        if (code !== "0000") {
            alert("获取奖品列表信息失败 code:" + code + " info：" + info);
            return;
        }
        console.log(data);
        const updatedPrizes = prizes.map((prize: Prize, index: number) => {
            const award: RaffleAwardType = data[index];
            return {
                ...prize,
                fonts: [{id: award.awardId, text: award.awardTitle, top: '35%'}]
            }
        });
        setPrizes(updatedPrizes);
    }

    const randomRaffleHandler = async () => {
        const queryParams = new URLSearchParams(window.location.search);
        const strategyId = Number(queryParams.get('strategyId'));
        const result = await randomRaffle(strategyId);
        const {code, info, data} = result;
        if (code !== "0000") {
            alert("获取随机奖品信息失败 code:" + code + " info：" + info);
            return;
        }
        console.log(data)
        return data.awardIndex - 1;
    }

    const [buttons] = useState([
        {x: 1, y: 1, background: "#7f95d1", fonts: [{text: '开始', top: '35%'}]}
    ])

    const [defaultStyle] = useState([{background: "#b8c5f2"}])

    const myLucky = useRef()

    useEffect(() => {
        queryRaffleAwardListHandler().then(r => {
        })
    }, []);

    return <>
        <LuckyGrid
            ref={myLucky}
            width="300px"
            height="300px"
            rows="3"
            cols="3"
            prizes={prizes}
            defaultStyle={defaultStyle}
            buttons={buttons}
            onStart={() => { // 点击抽奖按钮会触发star回调
                // @ts-ignore
                myLucky.current.play()
                setTimeout(() => {
                    randomRaffleHandler().then(index => {
                        // @ts-ignore
                        myLucky.current.stop(index)
                    })
                }, 2500)
            }}
            onEnd={
                // @ts-ignore
                prize => {
                    alert('恭喜你抽到【' + prize.fonts[0].text + '】奖品ID【' + prize.fonts[0].id + '】')
                }
            }>

        </LuckyGrid>
    </>

}
