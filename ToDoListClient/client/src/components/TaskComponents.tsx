import DeleteIcon from '@mui/icons-material/Delete';
import EditIcon from '@mui/icons-material/Edit';
import CheckIcon from '@mui/icons-material/Check';
import {
	Box,
	Checkbox,
	Divider,
	IconButton,
	Paper,
	TextField,
	styled,
	useTheme,
} from '@mui/material';
import { useState } from 'react';
import { Task } from '@types';
import { useTodos } from '@store';

interface TaskProps {
	task: Task;
}

const TaskComponent = ({ task }: TaskProps) => {
	const theme = useTheme();

	const deleteTodo = useTodos((state) => state.deleteTodo);
	const editTodo = useTodos((state) => state.editTodo);
	const doneTodo = useTodos((state) => state.doneTodo);

	const [input, setInput] = useState<string>(task.title);
	const [edit, setEdit] = useState<boolean>(false);

	const editTask = () => {
		editTodo({ id: task.id, title: input, done: task.done });
		setEdit(!edit);
	};

	const doneTask = () => {
		doneTodo({ id: task.id, title: task.title, done: !task.done });
	};

	return (
		<WrapperTask
			sx={{
				backgroundColor: task.done
					? theme.palette.backgroundColors.dark
					: 'none',
			}}
		>
			<WrapperBox>
				<Checkbox checked={task.done || false} onChange={doneTask} />

				<Divider sx={{ height: 28, m: '4px' }} orientation="vertical" />

				{edit ? (
					<TextField
						label="Редактировать задачу"
						variant="standard"
						multiline
						fullWidth
						sx={{ p: '0px 8px 8px 8px' }}
						InputProps={InputProps}
						inputProps={{ maxLength: 100 }}
						value={input}
						onChange={(e) => setInput(e.target.value)}
					/>
				) : (
					<TextField
						variant="standard"
						multiline
						fullWidth
						sx={{ p: 1, input: { cursor: 'pointer' } }}
						style={
							task.done
								? {
										textDecoration: 'line-through',
								  }
								: { textDecoration: 'none' }
						}
						InputProps={InputPropsReadOnly}
						value={task.title}
					/>
				)}

				<WrapperBox>
					{edit ? (
						<IconButton sx={{ m: 1 }} onClick={() => editTask()}>
							<CheckIcon />
						</IconButton>
					) : (
						<IconButton sx={{ m: 1 }} onClick={() => setEdit(!edit)}>
							<EditIcon />
						</IconButton>
					)}

					<Divider sx={{ height: 28, p: '4px' }} orientation="vertical" />

					<IconButton sx={{ m: 1 }} onClick={() => deleteTodo(task.id)}>
						<DeleteIcon />
					</IconButton>
				</WrapperBox>
			</WrapperBox>
		</WrapperTask>
	);
};

export default TaskComponent;

const InputProps = {
	disableUnderline: true,
};

const InputPropsReadOnly = {
	readOnly: true,
	disableUnderline: true,
};

const WrapperTask = styled(Paper)({
	margin: '16px 8px',
});

const WrapperBox = styled(Box)({
	display: 'flex',
	alignItems: 'center',
});
