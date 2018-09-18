export class Tour {
    constructor(
        public id: number,
        public date: Date,
        public description: string,
        public cost: number,
        public countryId: number
    ) {}
}