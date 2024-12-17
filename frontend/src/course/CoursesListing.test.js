import { render, screen } from "../test-utils";
import userEvent from "@testing-library/user-event";
import  CoursesListing from "./index";

describe('CoursesListing', () => {
    test('renders courses names correctly (as table cells)', async () => {
        render(<CoursesListing />);
        const revival = await screen.findByRole('cell', { 'name': 'Raccoon Revival' });
        expect(revival).toBeInTheDocument();

        const patrol = await screen.findByRole('cell', { 'name': 'How to Patrol' });
        expect(patrol).toBeInTheDocument();
    });

    test('renders course PetTypes names correctly (as items in a list)', async () => {
        render(<CoursesListing />);
        const revival = await screen.findByRole('cell', { 'name': 'Raccoon Revival' });
        const listitems=await screen.getAllByRole('listitem');
        const raccoon = listitems.find(listitem => listitem.textContent === 'raccoon')
        expect(raccoon).toBeInTheDocument();

        const mapuche = listitems.find(listitem => listitem.textContent === 'mapuche')
        expect(mapuche).toBeInTheDocument();
    });

});