// Finds an option value from the options of a select element (which may be group using optgroup elements)
// noinspection BadExpressionStatementJS
/**
 * Getter for the value of an option with a value in a specific group
 * This function will wait up to specified timeout, to check for changes in
 * the datalist.
 *
 * @param {HTMLInputElement} node the input element with the data list
 * @param {{timeout: number, parameter: string}} arg the argument
 * @returns {Promise<string>} the promise resolving to the full value of the matching option
 */
(node, {timeout, parameter}) => new Promise((resolve, reject) => {
    const prefix = parameter;
    const dataList = node.list
    if (dataList === null) {
        reject(new Error('The node has no data list'));
        return;
    }

    let counter = timeout * 20;

    function checkOptions() {
        const options = Array();
        for (const child of dataList.options) {
            options.push(child.value);
        }

        counter--;
        if (counter === 0) {
            reject(new Error(`No option starting with '${prefix}' found in ${timeout} seconds. Available options are: ${options.join(', ')}`));
            return;
        }

        const optionStartingWith = options.find(value => value.startsWith(prefix));
        if (optionStartingWith !== undefined) {
            resolve(optionStartingWith);
        } else {
            setTimeout(checkOptions, 50);
        }
    }

    checkOptions();
})