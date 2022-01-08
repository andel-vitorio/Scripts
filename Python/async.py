from datetime import date, datetime
import asyncio

class moment:
    async def getDate():
        today = await datetime.now().strftime('%d-%m-%Y')
        return today

    def getHour():
        hour = datetime.now().strftime('%H:%M:%S')
        return hour

async def mytask (id):
    print(f'Start Task {id}')


    print (f'{moment.getDate()}')
    
    print(f'End Task {id}')

async def main ():
    task1 = asyncio.create_task(mytask(1))
    task2 = asyncio.create_task(mytask(2))

asyncio.run(main())